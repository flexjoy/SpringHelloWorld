package com.springapp;

import org.springframework.stereotype.Component;

/**
 * List of static strings that determine the URL path.
 *
 * @author Sergey Cherepanov
 */
@Component("Url")
public final class Url {
    public static final String HOME_PAGE        = "/";
    public static final String SHOW_PERSON      = "/person/show";
    public static final String ADD_PERSON       = "/person/add";
    public static final String PERSON           = "/person/{id}";
    public static final String DELETE_PERSON    = "/person/delete";
    public static final String EDIT_PERSON      = "/person/edit/{id}";
    public static final String CONFIRM_DELETE   = "/confirmDelete/{id}";
    public static final String LOGIN            = "/login";
    public static final String LOGOUT           = "/logout";

    public static final String MAIN_CSS           = "/static/css/main.css";
    public static final String MAIN_JS           = "/static/js/main.js";
}
