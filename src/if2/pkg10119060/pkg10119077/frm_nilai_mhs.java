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
public class frm_nilai_mhs extends javax.swing.JFrame {
    koneksi dbsetting;
    String driver,database,user,pass;
    Object tabel;
    
    /**
     * Creates new form frm_nilai_mhs
     */
    
    public frm_nilai_mhs() {
        initComponents();
        dbsetting = new koneksi();
        driver = dbsetting.SettingPanel("DBDriver"); 
        database = dbsetting.SettingPanel("DBDatabase");
        user = dbsetting.SettingPanel("DBUsername");
        pass = dbsetting.SettingPanel("DBPassword ");
        tabel_nilai_mhs.setModel(tableModel);
        
        
        settableload();
        tampil_combo_nama();
        tampil_combo_kdmk();
        tahun_sekarang();
    }
     private javax.swing.table.DefaultTableModel tableModel=getDefaultTabelModel();
     private javax.swing.table.DefaultTableModel getDefaultTabelModel()
            {
//        judul header
        return new javax.swing.table.DefaultTableModel
        (
                new Object[][]{},
                new String [] {"Nama", "Nama MK", "Absensi", "Tugas 1", "Tugas 2", "Tugas3", "UTS", "UAS", "Nilai Absen", "Nilai Tugas", "Nilai UTS", "Nilai UAS", "Nilai Akhir", "Indeks", "Keterangan"}
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
     
     public void tahun_sekarang(){
         Date yn=new Date();
         SimpleDateFormat y=new SimpleDateFormat("yyyy");
         txt_angkatan.setText(y.format(yn));
     }
     
     public void tampil_combo_nama(){
        try{
            Class.forName(driver);
            Connection kon = DriverManager.getConnection(database, user, pass);
            Statement stt=kon.createStatement();
            String SQL = "select nama from t_mahasiswa order by nama";
            ResultSet res = stt.executeQuery(SQL);
            while(res.next()){
                Object[] ob1 = new Object[3];
                ob1[0] = res.getString(1);
                
            cb_nama.addItem((String) ob1[0]);
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
     
     String data[]=new String[15];
    private void settableload()
    {
        String stat = "";
        try
        {
            Class.forName(driver);
            Connection kon = DriverManager.getConnection(database,user,pass);
            Statement stt=kon.createStatement();
            String SQL = "select t_mahasiswa.nama, t_mata_kuliah.nama_mk, t_nilai.absensi, t_nilai.tugas1, t_nilai.tugas2, "
                            + "t_nilai.tugas3, t_nilai.uts, t_nilai.uas, t_nilai.nilai_absen, t_nilai.nilai_tugas, t_nilai.nilai_uts, "
                            + "t_nilai.nilai_uas, t_nilai.nilai_akhir, t_nilai.indeks, t_nilai.ket FROM t_mahasiswa, t_mata_kuliah, t_nilai "
                            + "WHERE t_nilai.kd_mk = t_mata_kuliah.kd_mk AND t_nilai.nim = t_mahasiswa.nim";
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
    
    public void membersihkan_teks()
        {
            txt_tugas1.setText("");
            txt_tugas2.setText("");
            txt_tugas3.setText("");
            txt_uts.setText("");
            txt_uas.setText("");
            txt_angkatan.setText("");
            cb_nama.setSelectedIndex(0);
            cb_mk.setSelectedIndex(0);
            txt_nim.setText("");
            txt_kehadiran.setText("");
            txt_kode_mk.setText("");
        }
        public void nonaktif_teks()
        {
            txt_nim.setEnabled(false);
            txt_kehadiran.setEnabled(false);
            txt_tugas1.setEnabled(false);
            txt_tugas2.setEnabled(false);
            txt_tugas3.setEnabled(false);
            txt_kode_mk.setEnabled(false);
            txt_uts.setEnabled(false);
            txt_uas.setEnabled(false);
            txt_angkatan.setEnabled(false);
            cb_mk.setEnabled(false);
            cb_nama.setEnabled(false);
            
        }
        public void aktif_teks()
        {
            txt_nim.setEnabled(true);
            txt_kehadiran.setEnabled(true);
            txt_tugas1.setEnabled(true);
            txt_tugas2.setEnabled(true);
            txt_tugas3.setEnabled(true);
            txt_kode_mk.setEnabled(true);
            txt_uts.setEnabled(true);
            txt_uas.setEnabled(true);
            txt_angkatan.setEnabled(true);
            cb_nama.setEnabled(true);
            cb_mk.setEnabled(true);
        }
        int row = 0;
        public void tampil_field()
        {
            row=tabel_nilai_mhs.getSelectedRow();
            
            cb_nama.setSelectedItem(tableModel.getValueAt(row, 0).toString());
            cb_mk.setSelectedItem(tableModel.getValueAt(row, 1).toString());
            txt_kehadiran.setText(tableModel.getValueAt(row, 2).toString());
            txt_tugas1.setText(tableModel.getValueAt(row, 3).toString());
            txt_tugas2.setText(tableModel.getValueAt(row, 4).toString());
            txt_tugas3.setText(tableModel.getValueAt(row, 5).toString());
            
            txt_uts.setText(tableModel.getValueAt(row, 6).toString());
            txt_uas.setText(tableModel.getValueAt(row, 7).toString());
            
            btn_simpan.setEnabled(false);
            btn_ubah.setEnabled(true);
            btn_hapus.setEnabled(true);
            btn_batal.setEnabled(false);
            aktif_teks();

        }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txt_cari = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        cb_nama = new javax.swing.JComboBox<>();
        txt_nim = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txt_kehadiran = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txt_tugas1 = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txt_tugas3 = new javax.swing.JTextField();
        txt_tugas2 = new javax.swing.JTextField();
        cb_mk = new javax.swing.JComboBox<>();
        jLabel11 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        txt_uts = new javax.swing.JTextField();
        txt_kode_mk = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        txt_uas = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        txt_angkatan = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabel_nilai_mhs = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        btn_tambah = new javax.swing.JButton();
        btn_ubah = new javax.swing.JButton();
        btn_batal = new javax.swing.JButton();
        btn_hapus = new javax.swing.JButton();
        btn_simpan = new javax.swing.JButton();
        btn_keluar = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Nilai Mahasiswa");
        setBackground(new java.awt.Color(51, 51, 255));
        addContainerListener(new java.awt.event.ContainerAdapter() {
            public void componentAdded(java.awt.event.ContainerEvent evt) {
                formComponentAdded(evt);
            }
        });
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        jPanel3.setBackground(new java.awt.Color(0, 153, 153));

        jLabel1.setBackground(new java.awt.Color(127, 255, 1));
        jLabel1.setFont(new java.awt.Font("Sylfaen", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("FORM NILAI MAHASISWA");

        jPanel2.setBackground(new java.awt.Color(0, 153, 153));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED), "Pencarian Data"));

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel2.setText("Masukan Data");

        txt_cari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_cariKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(72, 72, 72)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txt_cari, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(88, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txt_cari, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(38, Short.MAX_VALUE))
        );

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel3.setText("Nama");

        cb_nama.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "==PILIH NAMA==" }));
        cb_nama.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cb_nama.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_namaActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel4.setText("NIM");

        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel5.setText("Kehadiran");

        jLabel9.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
        jLabel9.setText("Pertemuan");

        jLabel6.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel6.setText("Tugas 1");

        jLabel7.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel7.setText("Tugas 2");

        jLabel8.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel8.setText("Tugas 3");

        cb_mk.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "==PILIH NAMA MK==" }));
        cb_mk.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cb_mk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_mkActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel11.setText("Kode MK");

        jLabel10.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel10.setText("Nama Mata Kuliah");

        jLabel13.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel13.setText("UTS");

        jLabel12.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel12.setText("UAS");

        jLabel14.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel14.setText("Angkatan");

        tabel_nilai_mhs.setBackground(new java.awt.Color(204, 204, 204));
        tabel_nilai_mhs.setModel(new javax.swing.table.DefaultTableModel(
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
        tabel_nilai_mhs.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabel_nilai_mhsMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabel_nilai_mhs);

        jPanel4.setBackground(new java.awt.Color(0, 153, 153));
        jPanel4.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(153, 255, 255), new java.awt.Color(102, 255, 255), null, null));

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

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
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
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_tambah, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_ubah, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_batal, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_hapus, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_simpan, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_keluar, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("Formulir Nilai Mahasiswa");

        jSeparator2.setOrientation(javax.swing.SwingConstants.VERTICAL);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                            .addGap(244, 244, 244)
                            .addComponent(jLabel1)))
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addGap(370, 370, 370)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(jLabel10)
                                        .addGap(18, 18, 18)
                                        .addComponent(cb_mk, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel11)
                                            .addComponent(jLabel13)
                                            .addComponent(jLabel12)
                                            .addComponent(jLabel14))
                                        .addGap(72, 72, 72)
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txt_uas, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txt_uts, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txt_kode_mk, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txt_angkatan, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGroup(jPanel3Layout.createSequentialGroup()
                                    .addComponent(jLabel15)
                                    .addGap(218, 218, 218)))
                            .addGap(26, 26, 26))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                            .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(207, 207, 207))))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(134, 134, 134)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel8))
                                .addGap(64, 64, 64)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txt_tugas3, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txt_tugas2, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txt_tugas1, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(cb_nama, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(txt_nim, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(jPanel3Layout.createSequentialGroup()
                                            .addComponent(txt_kehadiran, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(18, 18, 18)
                                            .addComponent(jLabel9))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(63, 63, 63)
                                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 81, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jLabel1)
                .addGap(36, 36, 36)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 16, Short.MAX_VALUE)
                .addComponent(jLabel15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel3)
                                    .addComponent(cb_nama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel4)
                                    .addComponent(txt_nim, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel5)
                                    .addComponent(txt_kehadiran, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel9))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel6)
                                    .addComponent(txt_tugas1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel7)
                                    .addComponent(txt_tugas2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel10)
                                    .addComponent(cb_mk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel11)
                                    .addComponent(txt_kode_mk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel13)
                                    .addComponent(txt_uts, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel12)
                                    .addComponent(txt_uas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel14)
                                    .addComponent(txt_angkatan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(txt_tugas3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jSeparator2, javax.swing.GroupLayout.DEFAULT_SIZE, 255, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        // TODO add your handling code here:
        frm_utama utama = new frm_utama();
        utama.setVisible(true);
    }//GEN-LAST:event_formWindowClosed

    private void formComponentAdded(java.awt.event.ContainerEvent evt) {//GEN-FIRST:event_formComponentAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_formComponentAdded

    private void txt_cariKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_cariKeyReleased
        // TODO add your handling code here:
         tableModel.setRowCount(0);
        try
        {
          Class.forName(driver);
                Connection kon  = DriverManager.getConnection(database, user, pass);
                Statement stt   = kon.createStatement();
                String SQL      = "SELECT * FROM `t_nilai` WHERE `kd_mk` LIKE'%"+txt_cari.getText()+"%'" + "OR nim LIKE '%" + txt_cari.getText() + "%'";
                ResultSet res=stt.executeQuery(SQL);
                while(res.next()) 
                {
                data[0] = res.getString(2);
                data[1] = res.getString(3);
                data[2] = res.getString(4);
                data[3] = res.getString(5);
                data[4] = res.getString(6);
                tableModel.addRow(data);
                }
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
    }//GEN-LAST:event_txt_cariKeyReleased

    private void cb_namaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_namaActionPerformed
        // TODO add your handling code here:
        String datacb_nama = (String) cb_nama.getSelectedItem();
        
        try
        {
          Class.forName(driver);
                Connection kon  = DriverManager.getConnection(database, user, pass);
                Statement stt   = kon.createStatement();
                String SQL      = "SELECT nim FROM `t_mahasiswa` WHERE `nama` LIKE'%"+datacb_nama+"%'";
                ResultSet res=stt.executeQuery(SQL);
                while(res.next()) 
                {
                data[0] = res.getString(1);
                }
                
                txt_nim.setText(data[0]);
                
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
        
    }//GEN-LAST:event_cb_namaActionPerformed

    private void cb_mkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_mkActionPerformed
        // TODO add your handling code here:
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

    private void btn_tambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_tambahActionPerformed
        // TODO add your handling code here:
            membersihkan_teks();
            txt_nim.requestFocus();
            btn_simpan.setEnabled(true);
            btn_ubah.setEnabled(false);
            btn_hapus.setEnabled(false);
            btn_keluar.setEnabled(true);
            btn_batal.setEnabled(true);
            aktif_teks();       
    }//GEN-LAST:event_btn_tambahActionPerformed

    private void btn_simpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_simpanActionPerformed
        // TODO add your handling code here:
        String indeks, ket;
        double nilai_absen = (((Double.parseDouble(txt_kehadiran.getText())/14)*100*5)/100);
        double nilai_tugas = ((Double.parseDouble(txt_tugas1.getText())+Double.parseDouble(txt_tugas2.getText())+Double.parseDouble(txt_tugas3.getText())/3)*(25/100));
        double nilai_uts = (Double.parseDouble(txt_uts.getText())*(30/100));
        double nilai_uas = (Double.parseDouble(txt_uas.getText())*(40/100));
        
        double nilai_akhir = ((((Double.parseDouble(txt_kehadiran.getText())/14)*100*5)/100))+(((Double.parseDouble(txt_tugas1.getText())+Double.parseDouble(txt_tugas2.getText())+Double.parseDouble(txt_tugas3.getText())/3)*(25/100)))+((Double.parseDouble(txt_uts.getText())*(30/100)))+((Double.parseDouble(txt_uas.getText())*(40/100)));
        if (nilai_akhir <= 100 && nilai_akhir >= 80) {
            indeks = "A";
            ket = "Lulus";
        } else if (nilai_akhir <= 79 && nilai_akhir >= 68) {
            indeks = "B";
            ket = "Lulus";
        } else if (nilai_akhir <= 67 && nilai_akhir >= 56) {
            indeks = "C";
            ket = "Lulus";
        } else if (nilai_akhir <= 55 && nilai_akhir >= 45) {
            indeks = "D";
            ket = "Tidak Lulus";
        } else {
            indeks = "E";
            ket = "Tidak Lulus";
        }
        
        
        if ((cb_nama.getSelectedItem() == null) || (cb_mk.getSelectedItem() == null) || (txt_kehadiran.getText().isEmpty()) || (txt_tugas1.getText().isEmpty()) || 
                (txt_tugas2.getText().isEmpty()) || (txt_tugas3.getText().isEmpty()) || (txt_uts.getText().isEmpty()) || (txt_uas.getText().isEmpty())) {
            JOptionPane.showMessageDialog(null, "data tidak boleh kosong, silahkan dilengkapi");
        }
        else{
            try{
                Class.forName(driver);
                Connection kon = DriverManager.getConnection(database, user, pass);
                Statement stt = kon.createStatement();
                String SQL = "INSERT INTO t_nilai(nim, kd_mk, absensi, tugas1, tugas2, tugas3, uts, uas, nilai_absen, nilai_tugas, nilai_uts, nilai_uas, nilai_akhir, indeks, ket) "
                        + "VALUES('"+txt_nim.getText()+"', '"+txt_kode_mk.getText()+"', "+Double.parseDouble(txt_kehadiran.getText())+", "+txt_tugas1.getText()+""
                        + ", "+txt_tugas2.getText()+", "+txt_tugas3.getText()+", "+txt_uts.getText()+", "+txt_uas.getText()+", "+nilai_absen+","
                        + " "+nilai_tugas+", "+nilai_uts+", "+nilai_uas+", "+nilai_akhir+", '"+indeks+"', '"+ket+"')";
            
            stt.executeUpdate(SQL);
            data[0] = txt_nim.getText();
            data[1] = txt_kode_mk.getText();
            data[2] = txt_kehadiran.getText();
            data[3] = txt_tugas1.getText();
            data[4] = txt_tugas2.getText();
            data[5] = txt_tugas3.getText();
            data[6] = txt_uts.getText();
            data[7] = txt_uas.getText();
            data[8] = Double.toString(nilai_absen);
            data[9] = Double.toString(nilai_tugas);
            data[10] = Double.toString(nilai_uts);
            data[11] = Double.toString(nilai_uas);
            data[12] = Double.toString(nilai_akhir);
            data[13] = indeks;
            data[14] = ket;
            
            tableModel.insertRow(0, data);
            stt.close();
            kon.close();
            membersihkan_teks();
            btn_simpan.setEnabled(false);
            
            }
            
            catch(Exception ex){
                JOptionPane.showMessageDialog(null, ex.getMessage(),"Error",JOptionPane.INFORMATION_MESSAGE);
            }
            
        }
        
    }//GEN-LAST:event_btn_simpanActionPerformed

    private void tabel_nilai_mhsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabel_nilai_mhsMouseClicked
         // TODO add your handling code here:
         if(evt.getClickCount()==1)
        {
            tampil_field();
            cb_nama.setEnabled(false); //untuk menutup combox enable
            cb_mk.setEnabled(false);
            txt_nim.setEnabled(false);
            txt_kode_mk.setEnabled(false);
            btn_batal.setEnabled(true);
        }
    }//GEN-LAST:event_tabel_nilai_mhsMouseClicked

    private void btn_batalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_batalActionPerformed
          // TODO add your handling code here:
            membersihkan_teks();
            btn_simpan.setEnabled(true);
            btn_ubah.setEnabled(true);
            btn_hapus.setEnabled(true);
            btn_keluar.setEnabled(true);
            btn_batal.setEnabled(true);
            aktif_teks();      
          
    }//GEN-LAST:event_btn_batalActionPerformed

    private void btn_hapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_hapusActionPerformed
        // TODO add your handling code here:
         try
        {
             Class.forName(driver);
                Connection kon  = DriverManager.getConnection(database, user, pass);
                Statement stt   = kon.createStatement();
                String SQL      = "DELETE From t_nilai "
                                    +"where "
                                    +"nim='"+tableModel.getValueAt(row, 0).toString()+"'";
                
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

    private void btn_ubahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ubahActionPerformed
        // TODO add your handling code here:
         String indeks, ket;
        double nilai_absen = (((Double.parseDouble(txt_kehadiran.getText())/14)*100*5)/100);
        double nilai_tugas = ((Double.parseDouble(txt_tugas1.getText())+Double.parseDouble(txt_tugas2.getText())+Double.parseDouble(txt_tugas3.getText())/3)*(25/100));
        double nilai_uts = (Double.parseDouble(txt_uts.getText())*(30/100));
        double nilai_uas = (Double.parseDouble(txt_uas.getText())*(40/100));
        
        double nilai_akhir = ((((Double.parseDouble(txt_kehadiran.getText())/14)*100*5)/100))+(((Double.parseDouble(txt_tugas1.getText())+Double.parseDouble(txt_tugas2.getText())+Double.parseDouble(txt_tugas3.getText())/3)*(25/100)))+((Double.parseDouble(txt_uts.getText())*(30/100)))+((Double.parseDouble(txt_uas.getText())*(40/100)));
        if (nilai_akhir <= 100 && nilai_akhir >= 80) {
            indeks = "A";
            ket = "Lulus";
        } else if (nilai_akhir <= 79 && nilai_akhir >= 68) {
            indeks = "B";
            ket = "Lulus";
        } else if (nilai_akhir <= 67 && nilai_akhir >= 56) {
            indeks = "C";
            ket = "Lulus";
        } else if (nilai_akhir <= 55 && nilai_akhir >= 45) {
            indeks = "D";
            ket = "Tidak Lulus";
        } else {
            indeks = "E";
            ket = "Tidak Lulus";
        }
        
        
        if ((cb_nama.getSelectedItem() == null) || (cb_mk.getSelectedItem() == null) || (txt_kehadiran.getText().isEmpty()) || (txt_tugas1.getText().isEmpty()) || 
                (txt_tugas2.getText().isEmpty()) || (txt_tugas3.getText().isEmpty()) || (txt_uts.getText().isEmpty()) || (txt_uas.getText().isEmpty())) {
            JOptionPane.showMessageDialog(null, "data tidak boleh kosong, silahkan dilengkapi");
        }
        else{
            try{
                Class.forName(driver);
                Connection kon = DriverManager.getConnection(database, user, pass);
                Statement stt = kon.createStatement();
                String SQL = "UPDATE t_nilai SET nim= '";
            
            stt.executeUpdate(SQL);
            data[0] = txt_nim.getText();
            data[1] = txt_kode_mk.getText();
            data[2] = txt_kehadiran.getText();
            data[3] = txt_tugas1.getText();
            data[4] = txt_tugas2.getText();
            data[5] = txt_tugas3.getText();
            data[6] = txt_uts.getText();
            data[7] = txt_uas.getText();
            data[8] = Double.toString(nilai_absen);
            data[9] = Double.toString(nilai_tugas);
            data[10] = Double.toString(nilai_uts);
            data[11] = Double.toString(nilai_uas);
            data[12] = Double.toString(nilai_akhir);
            data[13] = indeks;
            data[14] = ket;
            
            tableModel.insertRow(0, data);
            stt.close();
            kon.close();
            membersihkan_teks();
            btn_simpan.setEnabled(false);
            
            }
            
            catch(Exception ex){
                JOptionPane.showMessageDialog(null, ex.getMessage(),"Error",JOptionPane.INFORMATION_MESSAGE);
            }
    }//GEN-LAST:event_btn_ubahActionPerformed

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
            java.util.logging.Logger.getLogger(frm_nilai_mhs.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frm_nilai_mhs.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frm_nilai_mhs.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frm_nilai_mhs.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frm_nilai_mhs().setVisible(true);
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
    private javax.swing.JComboBox<String> cb_nama;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTable tabel_nilai_mhs;
    private javax.swing.JTextField txt_angkatan;
    private javax.swing.JTextField txt_cari;
    private javax.swing.JTextField txt_kehadiran;
    private javax.swing.JTextField txt_kode_mk;
    private javax.swing.JTextField txt_nim;
    private javax.swing.JTextField txt_tugas1;
    private javax.swing.JTextField txt_tugas2;
    private javax.swing.JTextField txt_tugas3;
    private javax.swing.JTextField txt_uas;
    private javax.swing.JTextField txt_uts;
    // End of variables declaration//GEN-END:variables
}
