package com.springapp;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Person class DAO interface implementation.
 *
 * @author Sergey Cherepanov
 */
public class PersonDaoImpl implements PersonDao {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public PersonDaoImpl(DataSource dataSource) {
        jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public long insert(Person person) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        Map<String, Object> namedParameters = new HashMap<>();
        namedParameters .put("name", person.getName());
        namedParameters .put("age", person.getAge());
        SqlParameterSource paramSource = new MapSqlParameterSource(namedParameters );
        String query = "INSERT INTO people (name, age) VALUES (:name, :age)";
        jdbcTemplate.update(query, paramSource, keyHolder);
        return keyHolder.getKey().longValue();
    }

    @Override
    public List<Person> selectAll() {
        String query = "SELECT id, name, age FROM people";
        BeanPropertyRowMapper mapper = new BeanPropertyRowMapper(Person.class);
        return jdbcTemplate.query(query, mapper);
    }

    @Override
    public Person selectPerson(long id) {
        String query = "SELECT id, name, age FROM people WHERE id = :id";
        SqlParameterSource paramSource = new MapSqlParameterSource("id", id);
        BeanPropertyRowMapper mapper = new BeanPropertyRowMapper(Person.class);
        List<Person> list = jdbcTemplate.query(query, paramSource, mapper);
        if (list.size() > 0)
            return list.get(0);
        else
            return null;
    }
}
