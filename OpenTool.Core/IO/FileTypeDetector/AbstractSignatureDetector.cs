using System;
using System.IO;

namespace OpenTool.Core.IO
{
    public abstract class AbstractSignatureDetector : IFileTypeDetector
    {
        public abstract string Extension { get; }

        public abstract string MimeType { get; }

        public abstract FileType FileType { get; }

        public bool Detect(Stream stream)
        {
            throw new NotImplementedException();
        }
    }
}