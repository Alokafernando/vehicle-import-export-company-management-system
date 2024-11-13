package lk.ijse.gdse.project.Model;

import lk.ijse.gdse.project.dto.ImportCompanyDTO;
import lk.ijse.gdse.project.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ImportCompanyModel {
    public String getNextImportComapnyID() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.execute("select company_ID from import_company order by company_ID desc limit 1");

        if (resultSet.next()) {
            String lastID = resultSet.getString(1);
            String subString = lastID.substring(1);
            int i = Integer.parseInt(subString);
            int newIndex = i+1;
            return String.format("I%03d", newIndex);
        }
        return "I001";
    }

    public ArrayList<ImportCompanyDTO> getAllImportCompanies() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.execute("select * from import_company");
        ArrayList<ImportCompanyDTO> importCompanies = new ArrayList<>();

        while (resultSet.next()) {
            ImportCompanyDTO importCompanyDTO = new ImportCompanyDTO(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5)
            );
            importCompanies.add(importCompanyDTO);
        }
        return importCompanies;
    }

    public boolean saveImportCompany(ImportCompanyDTO importCompanyDTO) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("insert into import_company values (?, ?, ?, ?, ?)",
                importCompanyDTO.getCompany_ID(),
                importCompanyDTO.getCompany_Name(),
                importCompanyDTO.getCountry(),
                importCompanyDTO.getContact(),
                importCompanyDTO.getEmail());
    }

    public boolean updateImportCompany(ImportCompanyDTO importCompanyDTO) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("update import_company set company_name=?, county=?, contact=?, email=? where company_ID=?",
                importCompanyDTO.getCompany_Name(),
                importCompanyDTO.getCountry(),
                importCompanyDTO.getContact(),
                importCompanyDTO.getEmail(),
                importCompanyDTO.getCompany_ID());
    }

    public boolean deleteImportCompany(String importCompanyID) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("delete from import_company where company_ID=?", importCompanyID);
    }
}
