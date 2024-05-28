using OpenTool.Core.Extensions;
using OpenTool.Crypto.Enum;
using Org.BouncyCastle.Crypto;
using Org.BouncyCastle.Crypto.Parameters;
using Org.BouncyCastle.Security;
using System.Diagnostics.CodeAnalysis;
using System.Text;

namespace OpenTool.Crypto.Symmetric
{
    public abstract class SymmetricCrypto : ISymmetricEncryptor, ISymmetricDecryptor
    {
        private ICipherParameters _key;
        private IBufferedCipher _bufferedCipher;
        protected abstract SymmetricAlgorithm Algorithm { get; }

        public SymmetricCrypto(Padding padding, string key)
           : this(Mode.ECB, padding, key)
        {
        }

        public SymmetricCrypto(Padding padding, string key, string iv)
            : this(Mode.CBC, padding, key, iv)
        {
        }

        public SymmetricCrypto(string algorithm, ICipherParameters key)
        {
            _bufferedCipher = CipherUtilities.GetCipher(algorithm);
            _key = key;
        }

        protected SymmetricCrypto(Mode mode, Padding padding, string key)
           : this(mode.ToString(), padding.ToString(), Encoding.UTF8.GetBytes(key))
        {
        }

        protected SymmetricCrypto(string mode, string padding, byte[] key)
        {
            _bufferedCipher = CipherUtilities.GetCipher($"{Algorithm}/{mode}/{padding}");
            _key = ParameterUtilities.CreateKeyParameter(Algorithm.ToString(), key);
        }

        protected SymmetricCrypto(Mode mode, Padding padding, string key, string iv)
            : this(mode.ToString(), padding.ToString(), Encoding.UTF8.GetBytes(key), Encoding.UTF8.GetBytes(iv))
        {
        }

        protected SymmetricCrypto(string mode, string padding, byte[] key, byte[] iv)
        {
            _bufferedCipher = CipherUtilities.GetCipher($"{Algorithm}/{mode}/{padding}");
            _key = new ParametersWithIV(ParameterUtilities.CreateKeyParameter(Algorithm.ToString(), key), iv);
        }

        public byte[] Decrypt(byte[] bytes)
        {
            _bufferedCipher.Reset();
            _bufferedCipher.Init(false, _key);
            return _bufferedCipher.DoFinal(bytes);
        }

        public string DecryptFromBase64(string str)
        {
            return Decrypt(str.Base64ToBytes()).BytesToString();
        }

        public string DecryptFromHex(string str)
        {
            return Decrypt(str.HexToBytes()).BytesToString();
        }

        public byte[] Encrypt(byte[] data)
        {
            _bufferedCipher.Reset();
            _bufferedCipher.Init(true, _key);
            return _bufferedCipher.DoFinal(data);
        }

        public string EncryptToBase64(string str)
        {
            return Encrypt(str.StringToBytes()).BytesToBase64String();
        }

        public string EncryptToHex(string str)
        {
            return Encrypt(str.StringToBytes()).BytesToHexString();
        }
    }
}