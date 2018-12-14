package util.fetcher;

public interface Fetcher {

    boolean hasNextPage();

    String nextPage();
}
