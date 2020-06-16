package hu.vizespalack.api;


public class Worker {

    private final String Id;

    public Worker(String id) {
        this.Id = id;
    }

    /**
     *
     * Returns the id of the worker.
     *
     * @return value of workerId
     *
     */
    public String getId() {
        return Id;
    }
}
