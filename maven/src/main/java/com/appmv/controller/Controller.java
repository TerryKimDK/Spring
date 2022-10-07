package com.appmv.controller;

import java.util.Map;
import java.util.Objects;

public interface Controller {
    String execute(Map<String, Objects> model)throws Exception;

    Objects[] getDataBinders();
}
