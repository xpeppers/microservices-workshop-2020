package com.xpeppers;

import static spark.Spark.*;

public class Main {
    public static void main(String[] args) {
        port(8282);
        get("/", (req, res) -> "Hello world");
    }

}
