package lk.ijse.gdse.project.Model;

import lk.ijse.gdse.project.dto.TaxDTO;
import lk.ijse.gdse.project.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TaxModel {
    public String getNextTaxID() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.execute("select tax_id from tax order by tax_id desc limit 1");

        if (resultSet.next()) {
            String lastID = resultSet.getString(1);
            String subString = lastID.substring(1);
            int i = Integer.parseInt(subString);
            int newIndex = i+1;
            return String.format("Ta%04d", newIndex);
        }
        return "Ta001";
    }

    public ArrayList<TaxDTO> getAllTaxes() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.execute("select * from tax");
        ArrayList<TaxDTO> taxes = new ArrayList<>();

        while (resultSet.next()) {
            TaxDTO tax = new TaxDTO(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    Double.parseDouble(resultSet.getString(3)),
                    Double.parseDouble(resultSet.getString(4)),
                    Double.parseDouble(resultSet.getString(5))
            );
            taxes.add(tax);
        }
        return taxes;
    }

    public boolean saveTax(TaxDTO taxDTO) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("insert into tax values (?, ?, ?, ?, ?)",
                taxDTO.getVehicle_id(),
                taxDTO.getTax_id(),
                taxDTO.getImport_tax(),
                taxDTO.getExport_tax(),
                taxDTO.getGround_tax());
    }

    public boolean updateTax(TaxDTO taxDTO) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("update tax set vehicle_id =?, import_tax =?, export_tax =? ground_tax =? where tax_id =?",
                taxDTO.getVehicle_id(),
                taxDTO.getTax_id(),
                taxDTO.getImport_tax(),
                taxDTO.getExport_tax(),
                taxDTO.getGround_tax());
    }
    public boolean deleteTax(String  taxId) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("delete from tax where tax_id =?", taxId);
    }
}
