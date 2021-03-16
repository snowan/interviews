public class Codec {

    Map<String, String> short2Long = new HashMap<>();
    Map<String, String> long2Short = new HashMap<>();
    private static final String SHORT_BASE_URL = "http://tinyurl.com/";
    private static final String CHAR_SETS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789"; // 26 + 26 + 10 -> (62^6)

    public String encode(String longUrl) {
        if (long2Short.containsKey(longUrl)) return SHORT_BASE_URL + long2Short.get(longUrl);
        String key = "";
        while (!key.isEmpty() && short2Long.containsKey(key)) {
            key = generateKey();
        }
        short2Long.put(key, longUrl);
        long2Short.put(longUrl, key);
        
        return SHORT_BASE_URL + key;
    }
    private String generateKey() {
        StringBuilder key = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            int random = (int)(Math.random() * CHAR_SETS.length());
            key.append(CHAR_SETS.charAt(random));
        }
        
        return key.toString(); 
    }

    // Decodes a shortened URL to its original URL.
    public String decode(String shortUrl) {
        return short2Long.get(shortUrl.replace(SHORT_BASE_URL, ""));
    }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.decode(codec.encode(url));
