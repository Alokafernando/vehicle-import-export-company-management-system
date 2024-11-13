package lk.ijse.gdse.project.Model;

import lk.ijse.gdse.project.dto.ExportCompanyDTO;
import lk.ijse.gdse.project.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ExportCompanyModel {
    public String getNextCompanyID() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.execute("select company_ID from export_company order by company_ID desc limit 1");

        if (resultSet.next()) {
            String lastID = resultSet.getString(1);
            String subString = lastID.substring(1);
            int i = Integer.parseInt(subString);
            int newIndex = i+1;
            return String.format("E%03d", newIndex);
        }
        return "E001";
    }

    public ArrayList<ExportCompanyDTO> getAllExportCompany() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.execute("select * from export_company");
        ArrayList<ExportCompanyDTO> exportCompanyDTOS = new ArrayList<>();

        while (resultSet.next()) {
            ExportCompanyDTO exportCompanyDTO = new ExportCompanyDTO(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5));

            exportCompanyDTOS.add(exportCompanyDTO);
        }
        return exportCompanyDTOS;
    }

    public boolean saveExportCompany(ExportCompanyDTO exportCompanyDTO) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("insert into export_company values(?,?,?,?,?)",
                exportCompanyDTO.getCompany_ID(),
                exportCompanyDTO.getCompany_Name(),
                exportCompanyDTO.getCountry(),
                exportCompanyDTO.getContact(),
                exportCompanyDTO.getEmail());
    }

    public boolean updateExportCompany(ExportCompanyDTO exportCompanyDTO) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("update export_company set company_name =?, county=?, contact=?, email=? where company_ID =?",
                exportCompanyDTO.getCompany_Name(),
                exportCompanyDTO.getCountry(),
                exportCompanyDTO.getContact(),
                exportCompanyDTO.getEmail(),
                exportCompanyDTO.getCompany_ID());
    }

    public boolean deleteExportCompany(String exportCompanyID) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("delete from export_company where company_ID  = ?", exportCompanyID);
    }
}
