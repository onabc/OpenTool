using System.IO;
using System.Text;
using YamlDotNet.Serialization;

namespace OpenTool.Core.Util
{
    public static class YamlUtil
    {
        private static Deserializer yamlDeserializer = new Deserializer();
        private static Serializer yamlSerializer = new Serializer();

        public static T ReadYaml<T>(string path)
        {
            if (!File.Exists(path)) throw new FileNotFoundException();

            var yaml = File.ReadAllText(path, Encoding.UTF8);
            return yamlDeserializer.Deserialize<T>(yaml); ;
        }

        public static void WriteYaml<T>(T data, string path)
        {
            if (!File.Exists(path)) throw new FileNotFoundException();
            var content = yamlSerializer.Serialize(data);
            File.WriteAllText(path, content, Encoding.UTF8);
        }
    }
}