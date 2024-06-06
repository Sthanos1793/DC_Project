package bench.cpu;

public class HashBreakerTask implements Runnable {
    private final String text;
    private final String hashToBreak;
    private final Hashmanager hasher;
    private final CPUThreadHashing parent;

    public HashBreakerTask(String text, String hashToBreak, Hashmanager hasher, CPUThreadHashing parent) {
        this.text = text;
        this.hashToBreak = hashToBreak;
        this.hasher = hasher;
        this.parent = parent;
    }

    @Override
    public void run() {
        if (hasher.hash(text).equals(hashToBreak)) {
            System.out.println("Password found: " + text);
            parent.setResult(text);
            parent.stop();
        }
    }
}
