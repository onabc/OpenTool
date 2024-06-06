using OpenTool.Crypto.Enum;
using Org.BouncyCastle.Crypto;
using Org.BouncyCastle.Crypto.Digests;
using System;
using System.Collections.Generic;

namespace OpenTool.Crypto.Digest
{
    public static class DigesterFactory
    {
        private static readonly Dictionary<DigestAlgorithm, IDigest> _digestPairs = new Dictionary<DigestAlgorithm, IDigest>
        {
            [DigestAlgorithm.MD2] = new MD2Digest(),
            [DigestAlgorithm.MD5] = new MD5Digest(),
            [DigestAlgorithm.SHA1] = new Sha1Digest(),
            [DigestAlgorithm.SHA256] = new Sha256Digest(),
            [DigestAlgorithm.SHA384] = new Sha384Digest(),
            [DigestAlgorithm.SHA512] = new Sha512Digest(),
            [DigestAlgorithm.SM3] = new SM3Digest()
        };

        public static IDigest GetDigestByAlgorithm(DigestAlgorithm algorithm)
        {
            if (!_digestPairs.TryGetValue(algorithm, out var digest))
                throw new MissingMemberException("missing digest algorithm");
            return digest;
        }
    }
}