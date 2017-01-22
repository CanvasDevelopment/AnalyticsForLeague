package com.teamunemployment.lolanalytics;

/**
 * @author Josiah Kendall
 */
public interface BasePresenterContract {
    void start();
    void handleError(Throwable e);
}
