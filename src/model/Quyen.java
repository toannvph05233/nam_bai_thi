package model;

public class Quyen {
    private String maQuyen;
    private String tenQuyen;
    private String maNhomQuyen;

    public Quyen() {
    }

    public Quyen(String maQuyen, String tenQuyen, String maNhomQuyen) {

        this.maQuyen = maQuyen;
        this.tenQuyen = tenQuyen;
        this.maNhomQuyen = maNhomQuyen;
    }

    public String getMaQuyen() {
        return maQuyen;
    }

    public void setMaQuyen(String maQuyen) {
        this.maQuyen = maQuyen;
    }

    public String getTenQuyen() {
        return tenQuyen;
    }

    public void setTenQuyen(String tenQuyen) {
        this.tenQuyen = tenQuyen;
    }

    public String getMaNhomQuyen() {
        return maNhomQuyen;
    }

    public void setMaNhomQuyen(String maNhomQuyen) {
        this.maNhomQuyen = maNhomQuyen;
    }
}