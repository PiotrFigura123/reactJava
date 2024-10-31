package pl.piotrfigura.reactive.s10.task;

class ServerError extends RuntimeException{

    public ServerError() {
        super("server error");
    }
}
