
package com.sandro.venta.api.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SellerResponse {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("control_id")
    @Expose
    private Integer controlId;
    @SerializedName("CODEMPRESA")
    @Expose
    private Object cODEMPRESA;
    @SerializedName("TIPOLOC")
    @Expose
    private Object tIPOLOC;
    @SerializedName("CODLOC")
    @Expose
    private Object cODLOC;
    @SerializedName("CODTIE")
    @Expose
    private Object cODTIE;
    @SerializedName("CODVEN")
    @Expose
    private String cODVEN;
    @SerializedName("NOMVEN")
    @Expose
    private String nOMVEN;
    @SerializedName("DIRVEN")
    @Expose
    private String dIRVEN;
    @SerializedName("LOCVEN")
    @Expose
    private Object lOCVEN;
    @SerializedName("TELE01")
    @Expose
    private String tELE01;
    @SerializedName("TELE02")
    @Expose
    private Object tELE02;
    @SerializedName("FECING")
    @Expose
    private Object fECING;
    @SerializedName("SIGLAVEN")
    @Expose
    private Object sIGLAVEN;
    @SerializedName("ACTIVO")
    @Expose
    private Integer aCTIVO;
    @SerializedName("COBRADOR")
    @Expose
    private Integer cOBRADOR;
    @SerializedName("FECINIVIG")
    @Expose
    private Object fECINIVIG;
    @SerializedName("FECFINVIG")
    @Expose
    private Object fECFINVIG;
    @SerializedName("IDVENTA")
    @Expose
    private Integer iDVENTA;
    @SerializedName("IMEI")
    @Expose
    private String iMEI;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getControlId() {
        return controlId;
    }

    public void setControlId(Integer controlId) {
        this.controlId = controlId;
    }

    public Object getCODEMPRESA() {
        return cODEMPRESA;
    }

    public void setCODEMPRESA(Object cODEMPRESA) {
        this.cODEMPRESA = cODEMPRESA;
    }

    public Object getTIPOLOC() {
        return tIPOLOC;
    }

    public void setTIPOLOC(Object tIPOLOC) {
        this.tIPOLOC = tIPOLOC;
    }

    public Object getCODLOC() {
        return cODLOC;
    }

    public void setCODLOC(Object cODLOC) {
        this.cODLOC = cODLOC;
    }

    public Object getCODTIE() {
        return cODTIE;
    }

    public void setCODTIE(Object cODTIE) {
        this.cODTIE = cODTIE;
    }

    public String getCODVEN() {
        return cODVEN;
    }

    public void setCODVEN(String cODVEN) {
        this.cODVEN = cODVEN;
    }

    public String getNOMVEN() {
        return nOMVEN;
    }

    public void setNOMVEN(String nOMVEN) {
        this.nOMVEN = nOMVEN;
    }

    public String getDIRVEN() {
        return dIRVEN;
    }

    public void setDIRVEN(String dIRVEN) {
        this.dIRVEN = dIRVEN;
    }

    public Object getLOCVEN() {
        return lOCVEN;
    }

    public void setLOCVEN(Object lOCVEN) {
        this.lOCVEN = lOCVEN;
    }

    public String getTELE01() {
        return tELE01;
    }

    public void setTELE01(String tELE01) {
        this.tELE01 = tELE01;
    }

    public Object getTELE02() {
        return tELE02;
    }

    public void setTELE02(Object tELE02) {
        this.tELE02 = tELE02;
    }

    public Object getFECING() {
        return fECING;
    }

    public void setFECING(Object fECING) {
        this.fECING = fECING;
    }

    public Object getSIGLAVEN() {
        return sIGLAVEN;
    }

    public void setSIGLAVEN(Object sIGLAVEN) {
        this.sIGLAVEN = sIGLAVEN;
    }

    public Integer getACTIVO() {
        return aCTIVO;
    }

    public void setACTIVO(Integer aCTIVO) {
        this.aCTIVO = aCTIVO;
    }

    public Integer getCOBRADOR() {
        return cOBRADOR;
    }

    public void setCOBRADOR(Integer cOBRADOR) {
        this.cOBRADOR = cOBRADOR;
    }

    public Object getFECINIVIG() {
        return fECINIVIG;
    }

    public void setFECINIVIG(Object fECINIVIG) {
        this.fECINIVIG = fECINIVIG;
    }

    public Object getFECFINVIG() {
        return fECFINVIG;
    }

    public void setFECFINVIG(Object fECFINVIG) {
        this.fECFINVIG = fECFINVIG;
    }

    public Integer getIDVENTA() {
        return iDVENTA;
    }

    public void setIDVENTA(Integer iDVENTA) {
        this.iDVENTA = iDVENTA;
    }

    public String getIMEI() {
        return iMEI;
    }

    public void setIMEI(String iMEI) {
        this.iMEI = iMEI;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

}
