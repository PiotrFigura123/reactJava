package pl.piotrfigura.reactive.s10.task;

class CleintError extends RuntimeException{

    public CleintError() {
        super("bad request");
    }
}
