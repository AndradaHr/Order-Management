package org.example.bll;
import org.example.dao.BillDAO;
import org.example.model.Bill;
import org.example.model.Client;
import org.example.model.Product;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;

public class BillBLL {
    private BillDAO billDAO=new BillDAO();

        public void insertBill(int id, int orderId, int total, LocalDate localDate){
            billDAO.insert(new Bill(id, orderId,total,localDate));
        }

}

