/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package if2.pkg10119060.pkg10119077;

import javax.swing.*;
//fungsi import yang digunakan untuk SQL
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author RAF
 */
public class frm_simulasi_na extends javax.swing.JFrame {
    koneksi dbsetting;
    String driver,database,user,pass;
    Object tabel;
    /**
     * Creates new form frm_simulasi_na
     */
    public frm_simulasi_na() {
         initComponents();
        dbsetting = new koneksi();
        driver = dbsetting.SettingPanel("DBDriver"); 
        database = dbsetting.SettingPanel("DBDatabase");
        user = dbsetting.SettingPanel("DBUsername");
        pass = dbsetting.SettingPanel("DBPassword ");
        table_simulasi_na.setModel(tableModel);
        settableload();
        tampil_combo_kdmk();
    }
    
    
    
     private javax.swing.table.DefaultTableModel tableModel=getDefaultTabelModel();
     private javax.swing.table.DefaultTableModel getDefaultTabelModel()
            {
//        judul header
        return new javax.swing.table.DefaultTableModel
        (
                new Object[][]{},
                new String [] {"Nama MK", "Persentase Absen", "Persentase Tugas", "Persentase UTS", "Persentase UAS", "Absensi", "Tugas 1", "Tugas 2", "Tugas3", "UTS", "UAS", "Nilai Absen", "Nilai Tugas", "Nilai UTS", "Nilai UAS", "Nilai Akhir", "Indeks", "Keterangan"}
        )
        {
            boolean[] canEdit = new boolean[]
            {
                false, false, false, false, false
            };
            
            public boolean isCellEdittable(int rowIndex, int columnIndex)
            {
                return canEdit [columnIndex];
            }
            };
        }
     
      String data[]=new String[18];
      private void settableload()
    {
        String stat = "";
        try
        {
            Class.forName(driver);
            Connection kon = DriverManager.getConnection(database,user,pass);
            Statement stt=kon.createStatement();
            String SQL = "select t_mata_kuliah.nama_mk,t_simulasi.pabsen, t_simulasi.ptugas, t_simulasi.puts, t_simulasi.puas, t_simulasi.absensi, t_simulasi.tugas1, t_simulasi.tugas2, t_simulasi.tugas3, t_simulasi.uts, t_simulasi.uas, t_simulasi.nilai_absen, t_simulasi.nilai_tugas, t_simulasi.nilai_uts, t_simulasi.nilai_uas, t_simulasi.nilai_akhir, t_simulasi.indeks, t_simulasi.ket FROM t_simulasi JOIN t_mata_kuliah ON t_simulasi.kd_mk = t_mata_kuliah.kd_mk";
            ResultSet res = stt.executeQuery(SQL);
            while(res.next())
            {
                data[0] = res.getString(1);
                data[1] = res.getString(2);
                data[2] = res.getString(3);
                data[3] = res.getString(4);
                data[4] = res.getString(5);
                data[5] = res.getString(6);
                data[6] = res.getString(7);
                data[7] = res.getString(8);
                data[8] = res.getString(9);
                data[9] = res.getString(10);
                data[10] = res.getString(11);
                data[11] = res.getString(12);
                data[12] = res.getString(13);
                data[13] = res.getString(14);
                data[14] = res.getString(15);
                data[15] = res.getString(16);
                data[16] = res.getString(17);
                data[17] = res.getString(18);
                tableModel.addRow(data);
            }
               res.close();
               stt.close();
               kon.close();
        }
        catch (Exception ex)
        {
            System.err.println(ex.getMessage());
            JOptionPane.showMessageDialog(null, ex.getMessage(),"Error",JOptionPane.INFORMATION_MESSAGE);
            System.exit(0);
        }
    }

       public void tampil_combo_kdmk(){
        try{
            Class.forName(driver);
            Connection kon = DriverManager.getConnection(database, user, pass);
            Statement stt=kon.createStatement();
            String SQL = "select nama_mk from t_mata_kuliah order by nama_mk";
            ResultSet res = stt.executeQuery(SQL);
            while(res.next()){
                Object[] ob1 = new Object[3];
                ob1[0] = res.getString(1);
                
                cb_mk.addItem((String) ob1[0]);
            }
            res.close();
            stt.close();
            kon.close();
        }
        catch(Exception ex){
            System.err.println(ex.getMessage());
            JOptionPane.showMessageDialog(null, ex.getMessage(),"Error", JOptionPane.INFORMATION_MESSAGE);
            
            System.exit(0);
        }
    }
       
        int row = 0;
        public void tampil_field()
        {
            row=table_simulasi_na.getSelectedRow();
            
            cb_mk.setSelectedItem(tableModel.getValueAt(row, 0).toString());
            txt_persentase_absen.setText(tableModel.getValueAt(row, 1).toString());
            txt_persentase_tugas.setText(tableModel.getValueAt(row, 2).toString());
            txt_persentase_uts.setText(tableModel.getValueAt(row, 3).toString());
            txt_persentase_uas.setText(tableModel.getValueAt(row, 4).toString());
            txt_kehadiran.setText(tableModel.getValueAt(row, 5).toString());
            txt_tugas1.setText(tableModel.getValueAt(row, 6).toString());
            txt_tugas2.setText(tableModel.getValueAt(row, 7).toString());
            txt_tugas3.setText(tableModel.getValueAt(row, 8).toString());
            txt_uts.setText(tableModel.getValueAt(row, 9).toString());
            txt_uas.setText(tableModel.getValueAt(row, 10).toString());
            
            
            btn_simpan.setEnabled(false);
            btn_ubah.setEnabled(true);
            btn_hapus.setEnabled(true);
            btn_batal.setEnabled(false);
            aktif_teks();

        }
        
        public void aktif_teks()
        {
            txt_kehadiran.setEnabled(true);
            txt_persentase_tugas.setEnabled(true);
            txt_persentase_absen.setEnabled(true);
            txt_persentase_uas.setEnabled(true);
            txt_persentase_uts.setEnabled(true);
            txt_tugas1.setEnabled(true);
            txt_tugas2.setEnabled(true);
            txt_tugas3.setEnabled(true);
            txt_kode_mk.setEnabled(true);
            txt_uts.setEnabled(true);
            txt_uas.setEnabled(true);
            cb_mk.setEnabled(true);
        }
        
         public void nonaktif_teks()
        {
            cb_mk.setEnabled(false);
            txt_kehadiran.setEnabled(false);
            txt_persentase_tugas.setEnabled(false);
            txt_persentase_absen.setEnabled(false);
            txt_persentase_uas.setEnabled(false);
            txt_persentase_uts.setEnabled(false);
            txt_tugas1.setEnabled(false);
            txt_tugas2.setEnabled(false);
            txt_tugas3.setEnabled(false);
            txt_kode_mk.setEnabled(false);
            txt_uts.setEnabled(false);
            txt_uas.setEnabled(false);
        }
        
          public void membersihkan_teks()
        {
            cb_mk.setSelectedIndex(0);
            txt_kehadiran.setText("");
            txt_persentase_tugas.setText("");
            txt_persentase_absen.setText("");
            txt_persentase_uas.setText("");
            txt_persentase_uts.setText("");
            txt_tugas1.setText("");
            txt_tugas2.setText("");
            txt_tugas3.setText("");
            txt_kode_mk.setText("");
            txt_uts.setText("");
            txt_uas.setText("");
        }
          
          
        
       
       
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txt_kode_mk = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        txt_persentase_uts = new javax.swing.JTextField();
        txt_persentase_tugas = new javax.swing.JTextField();
        txt_persentase_absen = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        txt_persentase_uas = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txt_tugas1 = new javax.swing.JTextField();
        txt_kehadiran = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txt_tugas2 = new javax.swing.JTextField();
        txt_tugas3 = new javax.swing.JTextField();
        txt_uts = new javax.swing.JTextField();
        txt_uas = new javax.swing.JTextField();
        cb_mk = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        table_simulasi_na = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        btn_tambah = new javax.swing.JButton();
        btn_ubah = new javax.swing.JButton();
        btn_batal = new javax.swing.JButton();
        btn_hapus = new javax.swing.JButton();
        btn_simpan = new javax.swing.JButton();
        btn_keluar = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Form Simulasi NIlai");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(0, 153, 153));

        jLabel1.setBackground(new java.awt.Color(127, 255, 1));
        jLabel1.setFont(new java.awt.Font("Sylfaen", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("FORM SIMULASI NILAI AKHIR");

        jLabel10.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel10.setText("Nama Mata Kuliah");

        jLabel11.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel11.setText("Kode MK");

        jLabel12.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel12.setText("Persentase Absen");

        jLabel13.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel13.setText("Persentase Tugas");

        jLabel14.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel14.setText("Persentase UTS");

        jLabel15.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel15.setText("Persentase UAS");

        jLabel16.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel16.setText("%");

        jLabel17.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel17.setText("%");

        jLabel18.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel18.setText("%");

        jLabel19.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel19.setText("%");

        jLabel21.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel21.setText("UAS");

        jLabel20.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel20.setText("UTS");

        jLabel8.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel8.setText("Tugas 3");

        jLabel7.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel7.setText("Tugas 2");

        jLabel6.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel6.setText("Tugas 1");

        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel5.setText("Kehadiran");

        jLabel9.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
        jLabel9.setText("Pertemuan");

        cb_mk.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "==PILIH NAMA MK==" }));
        cb_mk.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cb_mk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_mkActionPerformed(evt);
            }
        });

        table_simulasi_na.setBackground(new java.awt.Color(204, 204, 204));
        table_simulasi_na.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        table_simulasi_na.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                table_simulasi_naMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(table_simulasi_na);

        jPanel3.setBackground(new java.awt.Color(0, 153, 153));
        jPanel3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(153, 255, 255), new java.awt.Color(102, 255, 255), null, null));

        btn_tambah.setBackground(new java.awt.Color(153, 255, 0));
        btn_tambah.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        btn_tambah.setText("Tambah");
        btn_tambah.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(102, 255, 255), new java.awt.Color(153, 255, 255), null, null));
        btn_tambah.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_tambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_tambahActionPerformed(evt);
            }
        });

        btn_ubah.setBackground(new java.awt.Color(255, 255, 51));
        btn_ubah.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        btn_ubah.setText("Ubah");
        btn_ubah.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(102, 255, 255), new java.awt.Color(153, 255, 255), null, null));
        btn_ubah.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_ubah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ubahActionPerformed(evt);
            }
        });

        btn_batal.setBackground(new java.awt.Color(255, 0, 51));
        btn_batal.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        btn_batal.setText("Batal");
        btn_batal.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(102, 255, 255), new java.awt.Color(153, 255, 255), null, null));
        btn_batal.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_batal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_batalActionPerformed(evt);
            }
        });

        btn_hapus.setBackground(new java.awt.Color(255, 51, 51));
        btn_hapus.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        btn_hapus.setText("Hapus");
        btn_hapus.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(102, 255, 255), new java.awt.Color(153, 255, 255), null, null));
        btn_hapus.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_hapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_hapusActionPerformed(evt);
            }
        });

        btn_simpan.setBackground(new java.awt.Color(255, 204, 0));
        btn_simpan.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        btn_simpan.setText("Simpan");
        btn_simpan.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(102, 255, 255), new java.awt.Color(153, 255, 255), null, null));
        btn_simpan.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_simpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_simpanActionPerformed(evt);
            }
        });

        btn_keluar.setBackground(new java.awt.Color(255, 0, 0));
        btn_keluar.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        btn_keluar.setText("Keluar");
        btn_keluar.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(102, 255, 255), new java.awt.Color(153, 255, 255), null, null));
        btn_keluar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_keluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_keluarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(78, Short.MAX_VALUE)
                .addComponent(btn_tambah, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addComponent(btn_ubah, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39)
                .addComponent(btn_hapus, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37)
                .addComponent(btn_simpan, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(49, 49, 49)
                .addComponent(btn_batal, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42)
                .addComponent(btn_keluar, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(76, 76, 76))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_tambah, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_ubah, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_batal, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_hapus, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_simpan, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_keluar, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jSeparator2.setOrientation(javax.swing.SwingConstants.VERTICAL);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(448, 448, 448)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel21)
                            .addComponent(jLabel20))
                        .addGap(55, 55, 55)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_tugas2, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_tugas1, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_uas, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_uts, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_tugas3, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jScrollPane1)
                        .addContainerGap())))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 594, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(70, 70, 70))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addComponent(jLabel10)
                                    .addGap(45, 45, 45)
                                    .addComponent(cb_mk, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                            .addComponent(jLabel12)
                                            .addGap(51, 51, 51))
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jLabel11)
                                                .addComponent(jLabel15))
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                            .addComponent(txt_persentase_absen, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(18, 18, 18)
                                            .addComponent(jLabel17))
                                        .addComponent(txt_kode_mk, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                            .addComponent(txt_persentase_tugas, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(18, 18, 18)
                                            .addComponent(jLabel18))
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                            .addComponent(txt_persentase_uts, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(18, 18, 18)
                                            .addComponent(jLabel16))
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                            .addComponent(txt_persentase_uas, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(18, 18, 18)
                                            .addComponent(jLabel19)))))
                            .addComponent(jLabel13)
                            .addComponent(jLabel14))
                        .addGap(18, 18, 18)
                        .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addGap(18, 18, 18)
                                .addComponent(txt_kehadiran, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(23, 23, 23)
                                .addComponent(jLabel9))
                            .addComponent(jLabel8)
                            .addComponent(jLabel7)
                            .addComponent(jLabel6))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(57, 57, 57)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(78, 78, 78))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                .addGap(39, 39, 39)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel10)
                                    .addComponent(cb_mk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel11)
                                    .addComponent(txt_kode_mk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel12)
                                    .addComponent(txt_persentase_absen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel17))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel13)
                                    .addComponent(txt_persentase_tugas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel18))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel14)
                                    .addComponent(txt_persentase_uts, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel16))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txt_persentase_uas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel19)
                                    .addComponent(jLabel15)))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                .addGap(49, 49, 49)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel5)
                                    .addComponent(txt_kehadiran, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel9))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txt_tugas1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel6))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txt_tugas2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel7))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txt_tugas3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel8))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txt_uts, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel20))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txt_uas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel21))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        // TODO add your handling code here:
        frm_utama utama = new frm_utama();
         utama.setVisible(true);
    }//GEN-LAST:event_formWindowClosed

    private void cb_mkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_mkActionPerformed
        // TODO add your handling code here
        String datacb_mk = (String) cb_mk.getSelectedItem();
        
        try
        {
          Class.forName(driver);
                Connection kon  = DriverManager.getConnection(database, user, pass);
                Statement stt   = kon.createStatement();
                String SQL      = "SELECT kd_mk FROM `t_mata_kuliah` WHERE `nama_mk` LIKE'%"+datacb_mk+"%'";
                ResultSet res=stt.executeQuery(SQL);
                while(res.next()) 
                {
                data[0] = res.getString(1);
                }
                
                txt_kode_mk.setText(data[0]);
                
                res.close();
                stt.close();
                kon.close();
        }
        catch(Exception ex)
        {
           System.err.println(ex.getMessage());  
             JOptionPane.showMessageDialog(null, ex.getMessage(),"Error",JOptionPane.INFORMATION_MESSAGE);
              System.exit(0);
        }    
        
        
        
    }//GEN-LAST:event_cb_mkActionPerformed

    private void table_simulasi_naMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table_simulasi_naMouseClicked
         // TODO add your handling code here:
         
           if(evt.getClickCount()==1)
        {
            tampil_field();
            cb_mk.setEnabled(false);
            txt_kode_mk.setEnabled(false);
            btn_batal.setEnabled(true);
        }
    }//GEN-LAST:event_table_simulasi_naMouseClicked

    private void btn_tambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_tambahActionPerformed
        // TODO add your handling code here:
            txt_persentase_absen.requestFocus();
            btn_simpan.setEnabled(true);
            btn_ubah.setEnabled(false);
            btn_hapus.setEnabled(false);
            btn_keluar.setEnabled(true);
            btn_batal.setEnabled(true);
            aktif_teks();  
            membersihkan_teks();
    }//GEN-LAST:event_btn_tambahActionPerformed

    private void btn_batalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_batalActionPerformed
         // TODO add your handling code here:
            btn_simpan.setEnabled(true);
            btn_ubah.setEnabled(true);
            btn_hapus.setEnabled(true);
            btn_keluar.setEnabled(true);
            btn_batal.setEnabled(true);
            aktif_teks();     
            membersihkan_teks();
    }//GEN-LAST:event_btn_batalActionPerformed

    private void btn_keluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_keluarActionPerformed
         // TODO add your handling code here:
         dispose();
    }//GEN-LAST:event_btn_keluarActionPerformed

    private void btn_simpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_simpanActionPerformed
        // TODO add your handling code here:
          if((cb_mk.getSelectedItem()==("==PILIH KODE MK==") || txt_persentase_absen.getText().isEmpty() ||  txt_persentase_tugas.getText().isEmpty() ||  txt_persentase_uts.getText().isEmpty() ||  txt_persentase_uas.getText().isEmpty() || txt_kode_mk.getText().isEmpty() || txt_kehadiran.getText().isEmpty() || txt_tugas1.getText().isEmpty() || txt_tugas2.getText().isEmpty() || txt_tugas3.getText().isEmpty() || txt_uts.getText().isEmpty() || txt_uas.getText().isEmpty()))
        {
            JOptionPane.showMessageDialog(null,"Data Tidak Boleh kosong, silakan dilengkapi");
            txt_persentase_absen.requestFocus();
        }
        else
        {
            if (Double.valueOf(txt_kehadiran.getText()) > 14) {
                 JOptionPane.showMessageDialog(null,"Kehadiran tidak boleh lebih dari 14");
                 txt_kehadiran.requestFocus();
            }else{
                
         String data[]=new String[18];
         String indeks;
         String keterangan;
         double nilaiabsen = (((Double.valueOf(txt_kehadiran.getText())/14)*100*Double.valueOf(txt_persentase_absen.getText()))/100);
         double nilaitugas = (((Double.valueOf(txt_tugas1.getText())+Double.valueOf(txt_tugas2.getText())+Double.valueOf(txt_tugas3.getText()))/3)*Double.valueOf(txt_persentase_tugas.getText())/100);
         double nilaiuts   = (Double.valueOf(txt_uts.getText())*Double.valueOf(txt_persentase_uts.getText())/100);
         double nilaiuas   = (Double.valueOf(txt_uas.getText())*Double.valueOf(txt_persentase_uas.getText())/100);
         double nilaiakhir = nilaiabsen+nilaitugas+nilaiuts+nilaiuas;
         
          if(nilaiakhir >= 80 && nilaiakhir <=100){//kondisi saat nilai lebih dari 80
            indeks="A"; //indeks yang didapatkan
            keterangan="Lulus";
            }else if (nilaiakhir >=68 && nilaiakhir <= 79){//kondisi saat nilai 68 sampai 80
                indeks="B";//indeks yang didapatkan
                keterangan="Lulus";
            }else if (nilaiakhir >=55 && nilaiakhir <= 67){//kondisi saat nilai 56 sampai 68
                indeks="C";//indeks yang didapatkan
                keterangan="Lulus";
             }else if (nilaiakhir >=45 && nilaiakhir <= 54){//kondisi saat nilai 45 sampai 56
                indeks="D";//indeks yang didapatkan
                keterangan="Lulus";
            }else{ //jika tidak semuanya
                indeks="E";//indeks yang didapatkan
                keterangan = "Tidak Lulus";

            }
            try
            { 
               Class.forName(driver);
               Connection kon = DriverManager.getConnection(database, user, pass);
               Statement stt = kon.createStatement();
               String SQL = "INSERT INTO t_simulasi (kd_mk,pabsen,ptugas,puts,puas, absensi, tugas1, tugas2, tugas3, uts, uas, nilai_absen, nilai_tugas, nilai_uts, nilai_uas, nilai_akhir, indeks, ket) VALUES('"+txt_kode_mk.getText()+"',"+Double.valueOf(txt_persentase_absen.getText())+","+Double.valueOf(txt_persentase_tugas.getText())+","+Double.valueOf(txt_persentase_uts.getText())+","+Double.valueOf(txt_persentase_uas.getText())+","+Double.valueOf(txt_kehadiran.getText())+","+Double.valueOf(txt_tugas1.getText())+","+Double.valueOf(txt_tugas2.getText())+","+Double.valueOf(txt_tugas3.getText())+","+Double.valueOf(txt_uts.getText())+", "+Double.valueOf(txt_uas.getText())+","+nilaiabsen+", "+nilaitugas+", "+nilaiuts+", "+nilaiuas+", "+nilaiakhir+", '"+indeks+"', '"+keterangan+"')";
          
                stt.executeUpdate(SQL);
                data[0] = txt_kode_mk.getText();
                data[1] = txt_persentase_absen.getText();
                data[2] = txt_persentase_tugas.getText();
                data[3] = txt_persentase_uts.getText();
                data[4] = txt_persentase_uas.getText();
                data[5] = txt_kehadiran.getText();
                data[6] = txt_tugas1.getText();
                data[7] = txt_tugas2.getText();
                data[8] = txt_tugas3.getText();
                data[9] = txt_uts.getText();
                data[10] = txt_uas.getText();
                data[11] = String.valueOf(nilaiabsen);
                data[12] = String.valueOf(nilaitugas);
                data[13] = String.valueOf(nilaiuts);
                data[14] = String.valueOf(nilaiuas);
                data[15] = String.valueOf(nilaiakhir);
                data[16] = indeks;
                data[17] = keterangan;
   
                tableModel.insertRow(0, data);
                stt.close();
                kon.close();
                membersihkan_teks();
                btn_simpan.setEnabled(false);
                nonaktif_teks();
                
            }
            catch(Exception ex)
                    {
                        JOptionPane.showMessageDialog(null, ex.getMessage(),"Error",JOptionPane.INFORMATION_MESSAGE);
                    }
             
        }
        }
    }//GEN-LAST:event_btn_simpanActionPerformed

    private void btn_ubahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ubahActionPerformed
        // TODO add your handling code here:
        
        if((cb_mk.getSelectedItem()==("==PILIH KODE MK==") || txt_persentase_absen.getText().isEmpty() ||  txt_persentase_tugas.getText().isEmpty() ||  txt_persentase_uts.getText().isEmpty() ||  txt_persentase_uas.getText().isEmpty() || txt_kode_mk.getText().isEmpty() || txt_kehadiran.getText().isEmpty() || txt_tugas1.getText().isEmpty() || txt_tugas2.getText().isEmpty() || txt_tugas3.getText().isEmpty() || txt_uts.getText().isEmpty() || txt_uas.getText().isEmpty()))
        {
            JOptionPane.showMessageDialog(null,"Data Tidak Boleh kosong, silakan dilengkapi");
            txt_persentase_absen.requestFocus();
        }
        else
        {
            if (Double.valueOf(txt_kehadiran.getText()) > 14) {
                 JOptionPane.showMessageDialog(null,"Kehadiran tidak boleh lebih dari 14");
                 txt_kehadiran.requestFocus();
            }else{
                
         String data[]=new String[18];
         String indeks;
         String keterangan;
         double nilaiabsen = (((Double.valueOf(txt_kehadiran.getText())/14)*100*Double.valueOf(txt_persentase_absen.getText()))/100);
         double nilaitugas = (((Double.valueOf(txt_tugas1.getText())+Double.valueOf(txt_tugas2.getText())+Double.valueOf(txt_tugas3.getText()))/3)*Double.valueOf(txt_persentase_tugas.getText())/100);
         double nilaiuts   = (Double.valueOf(txt_uts.getText())*Double.valueOf(txt_persentase_uts.getText())/100);
         double nilaiuas   = (Double.valueOf(txt_uas.getText())*Double.valueOf(txt_persentase_uas.getText())/100);
         double nilaiakhir = nilaiabsen+nilaitugas+nilaiuts+nilaiuas;
         
          if(nilaiakhir >= 80 && nilaiakhir <=100){//kondisi saat nilai lebih dari 80
            indeks="A"; //indeks yang didapatkan
            keterangan="Lulus";
            }else if (nilaiakhir >=68 && nilaiakhir <= 79){//kondisi saat nilai 68 sampai 80
                indeks="B";//indeks yang didapatkan
                keterangan="Lulus";
            }else if (nilaiakhir >=55 && nilaiakhir <= 67){//kondisi saat nilai 56 sampai 68
                indeks="C";//indeks yang didapatkan
                keterangan="Lulus";
             }else if (nilaiakhir >=45 && nilaiakhir <= 54){//kondisi saat nilai 45 sampai 56
                indeks="D";//indeks yang didapatkan
                keterangan="Lulus";
            }else{ //jika tidak semuanya
                indeks="E";//indeks yang didapatkan
                keterangan = "Tidak Lulus";

            }
            try
            { 
               Class.forName(driver);
               Connection kon = DriverManager.getConnection(database, user, pass);
               Statement stt = kon.createStatement();
               String SQL =  "UPDATE t_simulasi SET kd_mk = '"+txt_kode_mk.getText()+"', "
                        + "pabsen = "+Double.valueOf(txt_persentase_absen.getText())+",ptugas = "+Double.valueOf(txt_persentase_tugas.getText())+","
                       + "puts = "+Double.valueOf(txt_persentase_uts.getText())+",puas = "+Double.valueOf(txt_persentase_uas.getText())+", "
                       + "absensi="+Double.valueOf(txt_kehadiran.getText())+", tugas1 = "+Double.valueOf(txt_tugas1.getText())+","
                        + "tugas2 = "+Double.valueOf(txt_tugas2.getText())+",tugas3 = "+Double.valueOf(txt_tugas3.getText())+","
                        + "uts = "+Double.valueOf(txt_uts.getText())+",uas = "+Double.valueOf(txt_uas.getText())+", nilai_absen = "+nilaiabsen+","
                        + "nilai_tugas="+nilaitugas+",nilai_uts= "+nilaiuts+",nilai_uas="+nilaiuas+",nilai_akhir = "+nilaiakhir+",indeks='"+indeks+"',ket='"+keterangan+"'"
                        + " WHERE kd_mk='"+txt_kode_mk.getText()+"'";
          
                stt.executeUpdate(SQL);
                data[0] = txt_kode_mk.getText();
                data[1] = txt_persentase_absen.getText();
                data[2] = txt_persentase_tugas.getText();
                data[3] = txt_persentase_uts.getText();
                data[4] = txt_persentase_uas.getText();
                data[5] = txt_kehadiran.getText();
                data[6] = txt_tugas1.getText();
                data[7] = txt_tugas2.getText();
                data[8] = txt_tugas3.getText();
                data[9] = txt_uts.getText();
                data[10] = txt_uas.getText();
                data[11] = String.valueOf(nilaiabsen);
                data[12] = String.valueOf(nilaitugas);
                data[13] = String.valueOf(nilaiuts);
                data[14] = String.valueOf(nilaiuas);
                data[15] = String.valueOf(nilaiakhir);
                data[16] = indeks;
                data[17] = keterangan;
   
                tableModel.removeRow(row);
                tableModel.insertRow(0, data);
                stt.close();
                kon.close();
                membersihkan_teks();
                btn_simpan.setEnabled(false);
                nonaktif_teks();
                
            }
            catch(Exception ex)
                    {
                        JOptionPane.showMessageDialog(null, ex.getMessage(),"Error",JOptionPane.INFORMATION_MESSAGE);
                    }
             
        }
        }
    }//GEN-LAST:event_btn_ubahActionPerformed

    private void btn_hapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_hapusActionPerformed
         // TODO add your handling code here:
           try
        {
             Class.forName(driver);
                Connection kon  = DriverManager.getConnection(database, user, pass);
                Statement stt   = kon.createStatement();
                String SQL      = "DELETE From t_simulasi "
                                    +"where "
                                    +"kd_mk = '"+txt_kode_mk.getText()+"'";
                stt.executeUpdate(SQL);
                tableModel.removeRow(row);
                stt.close();
                kon.close();
                membersihkan_teks();
        }
        catch (Exception ex)
        {
            System.err.println(ex.getMessage());
        }
    }//GEN-LAST:event_btn_hapusActionPerformed

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
            java.util.logging.Logger.getLogger(frm_simulasi_na.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frm_simulasi_na.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frm_simulasi_na.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frm_simulasi_na.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frm_simulasi_na().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_batal;
    private javax.swing.JButton btn_hapus;
    private javax.swing.JButton btn_keluar;
    private javax.swing.JButton btn_simpan;
    private javax.swing.JButton btn_tambah;
    private javax.swing.JButton btn_ubah;
    private javax.swing.JComboBox<String> cb_mk;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTable table_simulasi_na;
    private javax.swing.JTextField txt_kehadiran;
    private javax.swing.JTextField txt_kode_mk;
    private javax.swing.JTextField txt_persentase_absen;
    private javax.swing.JTextField txt_persentase_tugas;
    private javax.swing.JTextField txt_persentase_uas;
    private javax.swing.JTextField txt_persentase_uts;
    private javax.swing.JTextField txt_tugas1;
    private javax.swing.JTextField txt_tugas2;
    private javax.swing.JTextField txt_tugas3;
    private javax.swing.JTextField txt_uas;
    private javax.swing.JTextField txt_uts;
    // End of variables declaration//GEN-END:variables
}
