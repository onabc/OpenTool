using OpenTool.Core.Extensions;
using OpenTool.Crypto.Enum;
using Org.BouncyCastle.Crypto;
using Org.BouncyCastle.Security;
using System;

namespace OpenTool.Crypto.Asymmetric
{
    public abstract class AsymmetricCrypto : IAsymmetricEncryptor, IAsymmetricDecryptor
    {
        private ICipherParameters _privateKey;
        private ICipherParameters _publicKey;
        private IBufferedCipher _bufferedCipher;
        protected abstract AsymmetricAlgorithm Algorithm { get; }

        public AsymmetricCrypto(Mode mode, Padding padding, string privateKey, string publicKey)
           : this(mode.ToString(), padding.ToString(), privateKey, publicKey)
        {
        }

        protected AsymmetricCrypto(string mode, string padding, string privateKey, string publicKey)
        {
            _bufferedCipher = CipherUtilities.GetCipher($"{Algorithm}/{mode}/{padding}");
            if (!string.IsNullOrEmpty(publicKey)) _publicKey = PublicKeyFactory.CreateKey(Convert.FromBase64String(publicKey));
            if (!string.IsNullOrEmpty(privateKey)) _privateKey = PrivateKeyFactory.CreateKey(Convert.FromBase64String(privateKey));
        }

        public byte[] Decrypt(byte[] bytes, KeyType keyType)
        {
            var key = GetCipherParametersByKeyType(keyType);
            _bufferedCipher.Reset();
            _bufferedCipher.Init(false, key);
            return _bufferedCipher.DoFinal(bytes);
        }

        public string DecryptFromBase64(string str, KeyType keyType)
        {
            return Decrypt(str.Base64ToBytes(), keyType).BytesToString();
        }

        public string DecryptFromHex(string str, KeyType keyType)
        {
            return Decrypt(str.HexToBytes(), keyType).BytesToString();
        }

        public byte[] Encrypt(byte[] bytes, KeyType keyType)
        {
            var key = GetCipherParametersByKeyType(keyType);
            _bufferedCipher.Reset();
            _bufferedCipher.Init(true, key);
            return _bufferedCipher.DoFinal(bytes);
        }

        public string EncryptToBase64(string str, KeyType keyType)
        {
            return Encrypt(str.StringToBytes(), keyType).BytesToBase64String();
        }

        public string EncryptToHex(string str, KeyType keyType)
        {
            return Encrypt(str.StringToBytes(), keyType).BytesToHexString();
        }

        protected ICipherParameters GetCipherParametersByKeyType(KeyType keyType)
        {
            var parameters = keyType == KeyType.PublicKey ? _publicKey : _privateKey;
            if (parameters is null) throw new ArgumentNullException(nameof(parameters));
            return parameters;
        }
    }
}