package org.nihongo_deb.Scripts;

import java.sql.PreparedStatement;
import java.sql.Statement;

/**
 * @author KAWAIISHY
 * @project LibrarySpringMVC
 * @created 08.07.2023
 */
public interface LoaderMapper {
    void map(PreparedStatement statement, String[] values);

}
