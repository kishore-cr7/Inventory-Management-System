/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package supermarket;
import javax.swing.table.DefaultTableModel;
import javax.swing.JOptionPane;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.HeadlessException;
import java.sql.*;



        
public class selling extends javax.swing.JFrame {

        Connection con=null;
        PreparedStatement st=null;
        ResultSet rs=null;
    public selling() {
        initComponents();
        SelectProduct();
        getcat();
    }
    public void SelectProduct() {
    try {
        Class.forName("oracle.jdbc.driver.OracleDriver");
        con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "15-06-2005");
        st = con.prepareStatement("SELECT * FROM product order by pid");
        rs = st.executeQuery();
        ResultSetMetaData rsmd = rs.getMetaData();
        DefaultTableModel tbl = new DefaultTableModel();

        int col = rsmd.getColumnCount();
        String[] colname = new String[col];
        for (int i = 0; i < col; i++) {
            colname[i] = rsmd.getColumnName(i + 1);
        }

        tbl.setColumnIdentifiers(colname);

        while (rs.next()) {
            String pid = String.valueOf(rs.getInt(1));
            String pname = rs.getString(2);
            String pqunatity = String.valueOf(rs.getInt(3));
            String pprice = String.valueOf(rs.getInt(4));
            String pcate = rs.getString(5);  // Corrected variable name
            String[] tdata = {pid, pname, pqunatity, pprice, pcate};
            tbl.addRow(tdata);
        }
        producttable.setModel(tbl);
        con.close();
    } catch (Exception e) {
        System.out.println(e.getMessage());
    }
}
    int prid,newq;
public void update(){
    try (Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "15-06-2005");
         CallableStatement callableStatement = con.prepareCall("{ call update_product_quantity(?, ?) }")) {
        callableStatement.setInt(1, prid);
        callableStatement.setInt(2, newq);
        callableStatement.execute();
        SelectProduct();
    } catch (SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "Error updating record: " + e.getMessage());
    }
}
private void getcat(){
    try {
        Class.forName("oracle.jdbc.driver.OracleDriver");
        con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "15-06-2005");
        st = con.prepareStatement("SELECT * FROM category");
        rs = st.executeQuery();
        while(rs.next()){
            String mycat = rs.getString("name");
            pcate.addItem(mycat);
        }
    } catch (Exception e) {
        System.out.println(e.getMessage());
    }
}
   private boolean isQuantityValid(int productId, int enteredQuantity) {
     try (Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "15-06-2005");
         CallableStatement callableStatement = con.prepareCall("{ ? = call IS_QUANTITY_VALID(?, ?) }")) {

        // Register the OUT parameter
        callableStatement.registerOutParameter(1, Types.INTEGER);

        // Set the function parameters
        callableStatement.setInt(2, productId);
        callableStatement.setInt(3, enteredQuantity);

        // Execute the function
        callableStatement.execute();

        // Retrieve the result
        int result = callableStatement.getInt(1);
        return result == 1;
    } catch (SQLException e) {
        e.printStackTrace();
        // Handle the exception appropriately (e.g., log or display an error message)
        return false; // Return false if an error occurs
    }
} 

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        name = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        quantity = new javax.swing.JTextField();
        clrbtn = new javax.swing.JButton();
        ref = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        producttable = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        biltxt = new javax.swing.JTextArea();
        pcate = new javax.swing.JComboBox<>();
        billbtn = new javax.swing.JButton();
        print = new javax.swing.JButton();
        gtotb = new javax.swing.JLabel();
        ftr = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        billid = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(0, 204, 255));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jLabel2.setFont(new java.awt.Font("Arial Narrow", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 204, 255));
        jLabel2.setText("BILLING POINT");

        name.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nameActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 204, 255));
        jLabel4.setText("NAME");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 204, 255));
        jLabel5.setText("QUANTITY");

        quantity.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                quantityActionPerformed(evt);
            }
        });

        clrbtn.setBackground(new java.awt.Color(0, 204, 255));
        clrbtn.setText("CLEAR");
        clrbtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                clrbtnMouseClicked(evt);
            }
        });
        clrbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clrbtnActionPerformed(evt);
            }
        });

        ref.setBackground(new java.awt.Color(0, 204, 255));
        ref.setText("REFRESH");
        ref.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                refMouseClicked(evt);
            }
        });
        ref.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Arial Narrow", 1, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 204, 255));
        jLabel9.setText("PRODUCTS LIST");

        producttable.setBorder(javax.swing.BorderFactory.createCompoundBorder());
        producttable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "NAME", "QUANTITY", "PRICE", "CATEGORY"
            }
        ));
        producttable.setIntercellSpacing(new java.awt.Dimension(1, 1));
        producttable.setRowHeight(25);
        producttable.setSelectionBackground(new java.awt.Color(0, 204, 255));
        producttable.setSelectionForeground(new java.awt.Color(255, 255, 255));
        producttable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                producttableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(producttable);

        biltxt.setColumns(20);
        biltxt.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        biltxt.setRows(5);
        jScrollPane2.setViewportView(biltxt);

        pcate.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                pcateItemStateChanged(evt);
            }
        });
        pcate.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pcateMouseClicked(evt);
            }
        });
        pcate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pcateActionPerformed(evt);
            }
        });

        billbtn.setBackground(new java.awt.Color(0, 204, 255));
        billbtn.setText("ADD TO BILL");
        billbtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                billbtnMouseClicked(evt);
            }
        });
        billbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                billbtnActionPerformed(evt);
            }
        });

        print.setBackground(new java.awt.Color(0, 204, 255));
        print.setText("PRINT");
        print.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                printMouseClicked(evt);
            }
        });
        print.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                printActionPerformed(evt);
            }
        });

        gtotb.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        gtotb.setText("RS");

        ftr.setBackground(new java.awt.Color(0, 204, 255));
        ftr.setText("FILTER BY");
        ftr.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ftrMouseClicked(evt);
            }
        });
        ftr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ftrActionPerformed(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Arial Narrow", 1, 18)); // NOI18N
        jLabel12.setText("LOGOUT");
        jLabel12.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel12MouseClicked(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 204, 255));
        jLabel6.setText("BILL ID");

        billid.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                billidActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(252, 252, 252)
                        .addComponent(jLabel2))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(clrbtn)
                            .addComponent(billbtn)
                            .addComponent(jLabel5)
                            .addComponent(jLabel4)
                            .addComponent(ftr)
                            .addComponent(jLabel6))
                        .addGap(12, 12, 12)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 356, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(gtotb)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(print)))
                                .addGap(30, 30, 30)
                                .addComponent(jLabel12))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(quantity, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(name, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(pcate, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(ref)
                                    .addComponent(billid, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 356, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(20, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel9)
                .addGap(143, 143, 143))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
                .addComponent(jLabel9)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(pcate, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ftr))
                        .addGap(18, 18, 18)
                        .addComponent(ref)
                        .addGap(25, 25, 25)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel6)
                            .addComponent(billid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(quantity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(50, 50, 50)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(gtotb)
                            .addComponent(print)
                            .addComponent(jLabel12)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(billbtn)
                        .addGap(18, 18, 18)
                        .addComponent(clrbtn)))
                .addContainerGap(9, Short.MAX_VALUE))
        );

        jLabel8.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 18)); // NOI18N
        jLabel8.setText("X");
        jLabel8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel8MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(14, 14, 14))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void nameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nameActionPerformed

    private void quantityActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_quantityActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_quantityActionPerformed

    private void clrbtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_clrbtnMouseClicked
        
        name.setText("");
        quantity.setText("");
    }//GEN-LAST:event_clrbtnMouseClicked

    private void clrbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clrbtnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_clrbtnActionPerformed

    private void refMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_refMouseClicked
    SelectProduct();
    }//GEN-LAST:event_refMouseClicked

    private void refActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_refActionPerformed

    private void jLabel8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel8MouseClicked
        System.exit(0);
    }//GEN-LAST:event_jLabel8MouseClicked
Double uprice,prodtot=0.0,gtot=0.0;int aquan;
    private void producttableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_producttableMouseClicked
    DefaultTableModel model = (DefaultTableModel) producttable.getModel();
    int myindex = producttable.getSelectedRow();
    prid = Integer.valueOf(model.getValueAt(myindex, 0).toString());
    aquan = Integer.valueOf(model.getValueAt(myindex, 2).toString());
   
    uprice = Double.valueOf(model.getValueAt(myindex, 3).toString());
    name.setText(model.getValueAt(myindex, 1).toString());
    }//GEN-LAST:event_producttableMouseClicked

    private void pcateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pcateActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_pcateActionPerformed
int i=0;boolean isQuantityValid;
    private void billbtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_billbtnMouseClicked
    String quantityText = quantity.getText().trim();
    if (!quantityText.isEmpty() && quantityText.matches("\\d+")) {
        int quantityValue = Integer.parseInt(quantityText);
        prodtot = uprice * quantityValue; 
        isQuantityValid = isQuantityValid(prid, quantityValue);
    } else {
        
        JOptionPane.showMessageDialog(this, "Invalid quantity input");

    }
     
    if(quantity.getText().isEmpty()||name.getText().isEmpty())
    {
         JOptionPane.showMessageDialog(this, "Missing information");
    }else if(!isQuantityValid){
        JOptionPane.showMessageDialog(this, "not enough stockt");
    }
    else{
        newq = aquan - Integer.valueOf(quantity.getText());
        i++;
        gtot = gtot + prodtot;
        if(i==1){
            biltxt.setText(biltxt.getText()+"        =========PRAVEEN SHOP==========        \nBILL ID:"+billid.getText()+"\n"+" NUM        PRODUCT        PRICE        QUANTITY        TOTAL\n"+i+"        "+name.getText()+"        "+uprice+"        "+quantity.getText()+"        "+prodtot+"\n");
            
        }else{
            biltxt.setText(biltxt.getText()+i+"        "+name.getText()+"        "+uprice+"        "+quantity.getText()+"        "+prodtot+"\n");
            
        }
        gtotb.setText("RS "+gtot);
        update();
    }
    }//GEN-LAST:event_billbtnMouseClicked

    private void billbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_billbtnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_billbtnActionPerformed

    private void printMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_printMouseClicked
        try (Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "15-06-2005");
         Statement stmt = con.createStatement()) {

        // Fetch the next value from the sequence
        ResultSet rs = stmt.executeQuery("SELECT bill_id_seq.NEXTVAL FROM DUAL");

        if (rs.next()) {
            // Set the generated value in the billid textbox
            int newBillId = rs.getInt(1);
            billid.setText(String.valueOf(newBillId));
            biltxt.setText(" ");
        }

    } catch (SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "Error generating bill ID: " + e.getMessage());
    }
    }//GEN-LAST:event_printMouseClicked

    private void printActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_printActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_printActionPerformed

    private void jLabel12MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel12MouseClicked
        new login().setVisible(true);
     this.dispose();
    }//GEN-LAST:event_jLabel12MouseClicked

    private void pcateMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pcateMouseClicked
    
    }//GEN-LAST:event_pcateMouseClicked

    private void pcateItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_pcateItemStateChanged
       
    }//GEN-LAST:event_pcateItemStateChanged

    private void ftrMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ftrMouseClicked

          try {
        Class.forName("oracle.jdbc.driver.OracleDriver");
        con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "15-06-2005");

        // Assuming pcate is a JComboBox
        String selectedCategory = pcate.getSelectedItem().toString();

        // Use a parameterized query to avoid SQL injection
        st = con.prepareStatement("SELECT * FROM product WHERE pcategory = ?");
        st.setString(1, selectedCategory);

        rs = st.executeQuery();
        ResultSetMetaData rsmd = rs.getMetaData();
        DefaultTableModel tbl = new DefaultTableModel();

        int col = rsmd.getColumnCount();
        String[] colname = new String[col];
        for (int i = 0; i < col; i++) {
            colname[i] = rsmd.getColumnName(i + 1);
        }

        tbl.setColumnIdentifiers(colname);

        while (rs.next()) {
            String pid = String.valueOf(rs.getInt(1));
            String pname = rs.getString(2);
            String pqunatity = String.valueOf(rs.getInt(3));
            String pprice = String.valueOf(rs.getInt(4));
            String pcate = rs.getString(5);  // Corrected variable name
            String[] tdata = {pid, pname, pqunatity, pprice, pcate};
            tbl.addRow(tdata);
        }
        producttable.setModel(tbl);
        con.close();
    } catch (Exception e) {
        System.out.println(e.getMessage());
    }

         
    }//GEN-LAST:event_ftrMouseClicked

    private void ftrActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ftrActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ftrActionPerformed

    private void billidActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_billidActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_billidActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(selling.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(selling.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(selling.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(selling.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new selling().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton billbtn;
    private javax.swing.JTextField billid;
    private javax.swing.JTextArea biltxt;
    private javax.swing.JButton clrbtn;
    private javax.swing.JButton ftr;
    private javax.swing.JLabel gtotb;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField name;
    private javax.swing.JComboBox<String> pcate;
    private javax.swing.JButton print;
    private javax.swing.JTable producttable;
    private javax.swing.JTextField quantity;
    private javax.swing.JButton ref;
    // End of variables declaration//GEN-END:variables
}
