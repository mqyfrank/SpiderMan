package util.IpFetcher;

public interface Fetcher {

    boolean hasNextPage();

    String nextPage();
}
