package app.controller.commands;

import app.exceptions.HttpException;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;

public interface ICommand {
    String execute(HttpServletRequest req, String method) throws UnsupportedEncodingException, HttpException;
}
