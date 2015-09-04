package com.springapp.mvc;

import com.springapp.Person;
import com.springapp.Url;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.sql.DataSource;
import javax.validation.Valid;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

@Controller
public class HelloController {

    @Autowired
    private DataSource dataSource;

    @RequestMapping(Url.HOME_PAGE)
    public String showPerson(Model model, RedirectAttributes redirectAttributes) {
        String view = "showPersons";
        try (Connection conn = dataSource.getConnection()) {

            Statement st = conn.createStatement();
            ResultSet set = st.executeQuery("SELECT name, age FROM people");
            List<Person> personList = new ArrayList<>();

            while (set.next()) {
                String name = set.getString("name");
                int age = set.getInt("age");
                personList.add(new Person(name, age));
            }

            model.addAttribute("personList", personList);

        } catch (SQLException e) {

            redirectAttributes.addFlashAttribute("message", e.getMessage());
            view = "redirect:" + Url.ERROR_PAGE;

        }
        return view;
    }

    @RequestMapping(Url.ADD_PERSON)
    public String showForm(ModelMap model) {
        model.addAttribute("person", new Person());
        return "addPerson";
    }

    @RequestMapping(value = Url.ADD_PERSON, method = RequestMethod.POST)
    public String handlePersonForm(@Valid Person person, BindingResult result, RedirectAttributes redirectAttributes) {
        String view = "addPerson"; // if errors
        if (!result.hasErrors()){
            try (Connection conn = dataSource.getConnection()) {

                String query = "INSERT INTO people (name, age) VALUES (?, ?)";
                PreparedStatement stmt = conn.prepareStatement(query);
                stmt.setString(1, person.getName());
                stmt.setInt(2, person.getAge());
                stmt.execute();
                redirectAttributes.addFlashAttribute(person);
                view = "redirect:" + Url.HOME_PAGE;
            } catch (SQLException e) {

                redirectAttributes.addFlashAttribute("message", e.getMessage());
                view = "redirect:" + Url.ERROR_PAGE;

            }
        }
        return view;
    }

    @RequestMapping(Url.ERROR_PAGE)
    public String showError(Model model){
        return "error";
    }
}
