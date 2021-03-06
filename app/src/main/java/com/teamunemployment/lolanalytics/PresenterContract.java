package com.teamunemployment.lolanalytics;

/**
 * @author Josiah Kendall
 */
public interface PresenterContract {
    void start();
    void handleError(Throwable e);
    void restart();
    void resume();
    void pause();
    void stop();
    void destroy();
}
