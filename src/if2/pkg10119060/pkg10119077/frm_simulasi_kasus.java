/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package if2.pkg10119060.pkg10119077;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author ASUS
 */
public class frm_simulasi_kasus extends javax.swing.JFrame {
    koneksi dbsetting;
    String driver,database,user,pass;
    Object tabel;
    
    /**
     * Creates new form frm_simulasi_kasus
     */
    public frm_simulasi_kasus() {
        initComponents();
        dbsetting = new koneksi();
        driver = dbsetting.SettingPanel("DBDriver"); 
        database = dbsetting.SettingPanel("DBDatabase");
        user = dbsetting.SettingPanel("DBUsername");
        pass = dbsetting.SettingPanel("DBPassword ");
        tabel_pesanan.setModel(tableModel);
        
        settableload();
        tampil_combo_nama();
        tampil_combo_pegawai();
    }
    
    private javax.swing.table.DefaultTableModel tableModel=getDefaultTabelModel();
    private javax.swing.table.DefaultTableModel getDefaultTabelModel() {
        //judul header
        return new javax.swing.table.DefaultTableModel
        (
            new Object[][]{},
            new String [] {"No Pesanan", "Nama Barang", "Nama Pelanggan", "Nama Pegawai", "Harga","Jumlah", "Total Harga"}
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
    
    String data[]=new String[7];
    private void settableload()
    {
        String stat = "";
        try
        {
            Class.forName(driver);
            Connection kon = DriverManager.getConnection(database,user,pass);
            Statement stt=kon.createStatement();
            String SQL = "SELECT pesanan.nopesanan, barang.namabarang, pesanan.namapelanggan, pegawai.namapegawai, barang.harga,pesanan.jumlah, pesanan.totalharga"
                    + " FROM pesanan JOIN barang ON pesanan.idbarang=barang.idbarang JOIN pegawai ON pesanan.idpegawai=pegawai.idpegawai";
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
    
    public void tampil_combo_nama(){
        try{
            Class.forName(driver);
            Connection kon = DriverManager.getConnection(database, user, pass);
            Statement stt=kon.createStatement();
            String SQL = "SELECT namabarang from barang order by namabarang";
            ResultSet res = stt.executeQuery(SQL);
            while(res.next()){
                Object[] ob1 = new Object[3];
                ob1[0] = res.getString(1);
                
            cb_barang.addItem((String) ob1[0]);
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
    
    public void tampil_combo_pegawai(){
        try{
            Class.forName(driver);
            Connection kon = DriverManager.getConnection(database, user, pass);
            Statement stt=kon.createStatement();
            String SQL = "SELECT idpegawai from pegawai order by idpegawai";
            ResultSet res = stt.executeQuery(SQL);
            while(res.next()){
                Object[] ob1 = new Object[3];
                ob1[0] = res.getString(1);
                
            cb_pegawai.addItem((String) ob1[0]);
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
    
    public void membersihkan_teks()
        {
            cb_barang.setSelectedIndex(0);
            cb_pegawai.setSelectedIndex(0);
            txt_idbarang.setText("");
            txt_harga.setText("");
            txt_jumlah.setText("");
            txt_pelanggan.setText("");
            txt_cari.setText("");
        }
        public void nonaktif_teks()
        {
            cb_barang.setEnabled(false);
            cb_pegawai.setEnabled(false);
            txt_idbarang.setEnabled(false);
            txt_harga.setEnabled(false);
            txt_jumlah.setEnabled(false);
            txt_pelanggan.setEnabled(false);
            txt_cari.setEnabled(false);
        }
        public void aktif_teks()
        {
            cb_barang.setEnabled(true);
            cb_pegawai.setEnabled(true);
            txt_idbarang.setEnabled(true);
            txt_harga.setEnabled(true);
            txt_jumlah.setEnabled(true);
            txt_pelanggan.setEnabled(true);
            txt_cari.setEnabled(true);
        }
        int row = 0;
        public void tampil_field()
        {
            row=tabel_pesanan.getSelectedRow();
            
            cb_barang.setSelectedItem(tableModel.getValueAt(row, 1).toString());
            txt_harga.setText(tableModel.getValueAt(row, 4).toString());
            txt_jumlah.setText(tableModel.getValueAt(row, 5).toString());
            txt_pelanggan.setText(tableModel.getValueAt(row, 2).toString());
            
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

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        cb_barang = new javax.swing.JComboBox<>();
        txt_idbarang = new javax.swing.JTextField();
        txt_harga = new javax.swing.JTextField();
        txt_jumlah = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabel_pesanan = new javax.swing.JTable();
        btn_tambah = new javax.swing.JButton();
        btn_ubah = new javax.swing.JButton();
        btn_simpan = new javax.swing.JButton();
        btn_batal = new javax.swing.JButton();
        btn_keluar = new javax.swing.JButton();
        btn_hapus = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JSeparator();
        txt_cari = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        cb_pegawai = new javax.swing.JComboBox<>();
        jPanel2 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        txt_pelanggan = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(0, 153, 153));

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Transaksi Pembelian Barang");

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel2.setText("Nama Barang");

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel3.setText("Id Barang");

        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel4.setText("Harga");

        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel5.setText("Jumlah");

        cb_barang.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "=Pilih Barang=" }));
        cb_barang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_barangActionPerformed(evt);
            }
        });

        tabel_pesanan.setModel(new javax.swing.table.DefaultTableModel(
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
        tabel_pesanan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabel_pesananMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabel_pesanan);

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

        jSeparator2.setOrientation(javax.swing.SwingConstants.VERTICAL);

        txt_cari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_cariKeyReleased(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel6.setText("Cari Pesanan :");

        jLabel7.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel7.setText("Id Pegawai");

        cb_pegawai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "=Pilih ID=" }));
        cb_pegawai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_pegawaiActionPerformed(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(0, 153, 153));
        jPanel2.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel8.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel8.setText("Nama Pelanggan");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txt_pelanggan, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_pelanggan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1)
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(0, 235, Short.MAX_VALUE)
                                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 342, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addGap(18, 18, 18)
                                .addComponent(txt_cari, javax.swing.GroupLayout.PREFERRED_SIZE, 407, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel7)
                                        .addGap(63, 63, 63)
                                        .addComponent(cb_pegawai, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel5)
                                            .addComponent(jLabel3)
                                            .addComponent(jLabel4)
                                            .addComponent(jLabel2))
                                        .addGap(35, 35, 35)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(txt_harga, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(cb_barang, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(txt_idbarang, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txt_jumlah))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(48, 48, 48)))
                        .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btn_ubah, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_simpan, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_tambah, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_hapus, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_batal, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(84, 84, 84))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel1)
                        .addGap(182, 182, 182))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn_keluar, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btn_hapus, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(26, 26, 26)
                                .addComponent(btn_tambah, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btn_ubah, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(14, 14, 14)
                                .addComponent(btn_simpan, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 30, Short.MAX_VALUE)
                                .addComponent(btn_batal, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel2)
                                            .addComponent(cb_barang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel3)
                                            .addComponent(txt_idbarang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel4)
                                            .addComponent(txt_harga, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel5)
                                            .addComponent(txt_jumlah, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(62, 62, 62)
                                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel7)
                                    .addComponent(cb_pegawai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txt_cari, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jSeparator2, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24)
                .addComponent(btn_keluar, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btn_tambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_tambahActionPerformed
        // TODO add your handling code here:
            membersihkan_teks();
            btn_simpan.setEnabled(true);
            btn_ubah.setEnabled(false);
            btn_hapus.setEnabled(false);
            btn_keluar.setEnabled(true);
            btn_batal.setEnabled(true);
            aktif_teks();    

    }//GEN-LAST:event_btn_tambahActionPerformed

    private void btn_ubahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ubahActionPerformed
        // TODO add your handling code here:
        String data[]=new String[7];
        int total_harga = Integer.valueOf(txt_harga.getText()) * Integer.valueOf(txt_jumlah.getText());

        if(cb_barang.getSelectedItem()==("=Pilih Barang=") || (cb_pegawai.getSelectedItem()==("=Pilih ID=") || txt_idbarang.getText().isEmpty() || txt_harga.getText().isEmpty() || txt_jumlah.getText().isEmpty() || txt_pelanggan.getText().isEmpty()))
        {
            JOptionPane.showMessageDialog(null,"Data Tidak Boleh kosong, silakan dilengkapi");
        }
        else
        {     
            try
            {          
               Class.forName(driver);
               Connection kon = DriverManager.getConnection(database, user, pass);
               Statement stt = kon.createStatement();
               String SQL = "UPDATE pesanan SET idbarang='"+txt_idbarang.getText()+"',namapelanggan='"+txt_pelanggan.getText()+"',idpegawai="
                       + "'"+cb_pegawai.getSelectedItem()+"',jumlah="+txt_jumlah.getText()+",totalharga="+total_harga+" WHERE nopesanan = '"+tableModel.getValueAt(row, 0).toString()+"'";
          
                stt.executeUpdate(SQL);
                data[1] = txt_idbarang.getText();
                data[2] = txt_pelanggan.getText();
                data[3] = (String) cb_pegawai.getSelectedItem();
                data[4] = txt_harga.getText();
                data[5] = txt_jumlah.getText();
                data[6] = String.valueOf(total_harga);
                
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
    }//GEN-LAST:event_btn_ubahActionPerformed

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
        // TODO add your handling code 
        try
        {
             Class.forName(driver);
                Connection kon  = DriverManager.getConnection(database, user, pass);
                Statement stt   = kon.createStatement();
                String SQL      = "DELETE From pesanan "
                                    +"WHERE "
                                    +"nopesanan = '"+tableModel.getValueAt(row, 0).toString()+"'";
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

    private void btn_simpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_simpanActionPerformed
        // TODO add your handling code here:
        String data[]=new String[7];
        int total_harga = Integer.valueOf(txt_harga.getText()) * Integer.valueOf(txt_jumlah.getText());

        if(cb_barang.getSelectedItem()==("=Pilih Barang=") || (cb_pegawai.getSelectedItem()==("=Pilih ID=") || txt_idbarang.getText().isEmpty() || txt_harga.getText().isEmpty() || txt_jumlah.getText().isEmpty() || txt_pelanggan.getText().isEmpty()))
        {
            JOptionPane.showMessageDialog(null,"Data Tidak Boleh kosong, silakan dilengkapi");
        }
        else
        {     
            try
            {          
               Class.forName(driver);
               Connection kon = DriverManager.getConnection(database, user, pass);
               Statement stt = kon.createStatement();
               String SQL = "INSERT INTO pesanan(idbarang, namapelanggan, idpegawai, jumlah, totalharga) VALUES('"+txt_idbarang.getText()+"','"
                       + ""+txt_pelanggan.getText()+"','"+cb_pegawai.getSelectedItem()+"',"+txt_jumlah.getText()+","+total_harga+")";
          
                stt.executeUpdate(SQL);
                data[1] = txt_idbarang.getText();
                data[2] = txt_pelanggan.getText();
                data[3] = (String) cb_pegawai.getSelectedItem();
                data[4] = txt_harga.getText();
                data[5] = txt_jumlah.getText();
                data[6] = String.valueOf(total_harga);
                
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
    }//GEN-LAST:event_btn_simpanActionPerformed

    private void btn_keluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_keluarActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_btn_keluarActionPerformed

    private void cb_barangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_barangActionPerformed
        // TODO add your handling code here:
        String datacb = (String) cb_barang.getSelectedItem();
        
        try
        {
          Class.forName(driver);
                Connection kon  = DriverManager.getConnection(database, user, pass);
                Statement stt   = kon.createStatement();
                String SQL      = "SELECT barang.idbarang, barang.harga FROM barang WHERE namabarang LIKE'%"+datacb+"%'";
                ResultSet res=stt.executeQuery(SQL);
                while(res.next()) 
                {
                data[0] = res.getString(1);
                data[1] = res.getString(2);
                }
                
                txt_idbarang.setText(data[0]);
                txt_harga.setText(data[1]);
                
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
        
    }//GEN-LAST:event_cb_barangActionPerformed

    private void cb_pegawaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_pegawaiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cb_pegawaiActionPerformed

    private void tabel_pesananMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabel_pesananMouseClicked
        // TODO add your handling code here:
         if(evt.getClickCount()==1)
        {
            tampil_field();
            cb_barang.setEnabled(false); //untuk menutup combox enable
            txt_idbarang.setEnabled(false);
            txt_harga.setEnabled(false);
            btn_batal.setEnabled(true);
        }
    }//GEN-LAST:event_tabel_pesananMouseClicked

    private void txt_cariKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_cariKeyReleased
        // TODO add your handling code here:
        tableModel.setRowCount(0);
        try
        {
          Class.forName(driver);
                Connection kon  = DriverManager.getConnection(database, user, pass);
                Statement stt   = kon.createStatement();
                String SQL      = "SELECT pesanan.nopesanan, barang.namabarang, pesanan.namapelanggan, pegawai.namapegawai, barang.harga,pesanan.jumlah, pesanan.totalharga FROM pesanan JOIN barang ON pesanan.idbarang=barang.idbarang JOIN pegawai ON pesanan.idpegawai=pegawai.idpegawai WHERE namapelanggan LIKE'%"+txt_cari.getText()+"%'" + "OR nopesanan LIKE '%" + txt_cari.getText() + "%'";
                ResultSet res=stt.executeQuery(SQL);
                while(res.next()) 
                {
                data[0] = res.getString(1);
                data[1] = res.getString(2);
                data[2] = res.getString(3);
                data[3] = res.getString(4);
                data[4] = res.getString(5);
                data[5] = res.getString(6);
                data[6] = res.getString(7);
                
                
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

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        // TODO add your handling code here:
        frm_utama utama = new frm_utama();
        utama.setVisible(true);
    }//GEN-LAST:event_formWindowClosed
    
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
            java.util.logging.Logger.getLogger(frm_simulasi_kasus.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frm_simulasi_kasus.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frm_simulasi_kasus.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frm_simulasi_kasus.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frm_simulasi_kasus().setVisible(true);
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
    private javax.swing.JComboBox<String> cb_barang;
    private javax.swing.JComboBox<String> cb_pegawai;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTable tabel_pesanan;
    private javax.swing.JTextField txt_cari;
    private javax.swing.JTextField txt_harga;
    private javax.swing.JTextField txt_idbarang;
    private javax.swing.JTextField txt_jumlah;
    private javax.swing.JTextField txt_pelanggan;
    // End of variables declaration//GEN-END:variables
}
