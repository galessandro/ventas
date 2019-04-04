
package com.sandro.venta.api.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CustomerResponse {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("control_id")
    @Expose
    private Integer controlId;
    @SerializedName("FECING")
    @Expose
    private String fECING;
    @SerializedName("CODCLI")
    @Expose
    private String cODCLI;
    @SerializedName("NOMBRE")
    @Expose
    private String nOMBRE;
    @SerializedName("RESPONSA")
    @Expose
    private String rESPONSA;
    @SerializedName("NOMCLI")
    @Expose
    private String nOMCLI;
    @SerializedName("APEPAT")
    @Expose
    private String aPEPAT;
    @SerializedName("APEMAT")
    @Expose
    private String aPEMAT;
    @SerializedName("FECNAC")
    @Expose
    private String fECNAC;
    @SerializedName("CODENTIDAD")
    @Expose
    private String cODENTIDAD;
    @SerializedName("NDOCUMENTO")
    @Expose
    private String nDOCUMENTO;
    @SerializedName("DNI")
    @Expose
    private String dNI;
    @SerializedName("RUC")
    @Expose
    private String rUC;
    @SerializedName("PASAPORTE")
    @Expose
    private String pASAPORTE;
    @SerializedName("CARNET")
    @Expose
    private String cARNET;
    @SerializedName("CEDULA")
    @Expose
    private String cEDULA;
    @SerializedName("DIRCLI")
    @Expose
    private String dIRCLI;
    @SerializedName("DIS")
    @Expose
    private String dIS;
    @SerializedName("UBICLI")
    @Expose
    private String uBICLI;
    @SerializedName("UBIGEOCOMPLETO")
    @Expose
    private String uBIGEOCOMPLETO;
    @SerializedName("TELE01")
    @Expose
    private String tELE01;
    @SerializedName("TELE02")
    @Expose
    private String tELE02;
    @SerializedName("CELULAR")
    @Expose
    private String cELULAR;
    @SerializedName("EMAIL")
    @Expose
    private String eMAIL;
    @SerializedName("CODVEN")
    @Expose
    private String cODVEN;
    @SerializedName("FECUC")
    @Expose
    private String fECUC;
    @SerializedName("FECUP")
    @Expose
    private String fECUP;
    @SerializedName("CREDITO")
    @Expose
    private String cREDITO;
    @SerializedName("OBS1")
    @Expose
    private String oBS1;
    @SerializedName("OBS2")
    @Expose
    private String oBS2;
    @SerializedName("CAUTION")
    @Expose
    private Integer cAUTION;
    @SerializedName("CLASE")
    @Expose
    private String cLASE;
    @SerializedName("RUCLE")
    @Expose
    private String rUCLE;
    @SerializedName("ACTIVO")
    @Expose
    private Integer aCTIVO;
    @SerializedName("ZONA")
    @Expose
    private String zONA;
    @SerializedName("ZONAAUX")
    @Expose
    private String zONAAUX;
    @SerializedName("SEMAFORO")
    @Expose
    private String sEMAFORO;
    @SerializedName("FECINIVIG")
    @Expose
    private String fECINIVIG;
    @SerializedName("FECFINVIG")
    @Expose
    private String fECFINVIG;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("dniType")
    @Expose
    private String dniType;

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

    public String getFECING() {
        return fECING;
    }

    public void setFECING(String fECING) {
        this.fECING = fECING;
    }

    public String getCODCLI() {
        return cODCLI;
    }

    public void setCODCLI(String cODCLI) {
        this.cODCLI = cODCLI;
    }

    public String getNOMBRE() {
        return nOMBRE;
    }

    public void setNOMBRE(String nOMBRE) {
        this.nOMBRE = nOMBRE;
    }

    public String getRESPONSA() {
        return rESPONSA;
    }

    public void setRESPONSA(String rESPONSA) {
        this.rESPONSA = rESPONSA;
    }

    public String getNOMCLI() {
        return nOMCLI;
    }

    public void setNOMCLI(String nOMCLI) {
        this.nOMCLI = nOMCLI;
    }

    public String getAPEPAT() {
        return aPEPAT;
    }

    public void setAPEPAT(String aPEPAT) {
        this.aPEPAT = aPEPAT;
    }

    public String getAPEMAT() {
        return aPEMAT;
    }

    public void setAPEMAT(String aPEMAT) {
        this.aPEMAT = aPEMAT;
    }

    public String getFECNAC() {
        return fECNAC;
    }

    public void setFECNAC(String fECNAC) {
        this.fECNAC = fECNAC;
    }

    public String getCODENTIDAD() {
        return cODENTIDAD;
    }

    public void setCODENTIDAD(String cODENTIDAD) {
        this.cODENTIDAD = cODENTIDAD;
    }

    public String getNDOCUMENTO() {
        return nDOCUMENTO;
    }

    public void setNDOCUMENTO(String nDOCUMENTO) {
        this.nDOCUMENTO = nDOCUMENTO;
    }

    public String getDNI() {
        return dNI;
    }

    public void setDNI(String dNI) {
        this.dNI = dNI;
    }

    public String getRUC() {
        return rUC;
    }

    public void setRUC(String rUC) {
        this.rUC = rUC;
    }

    public String getPASAPORTE() {
        return pASAPORTE;
    }

    public void setPASAPORTE(String pASAPORTE) {
        this.pASAPORTE = pASAPORTE;
    }

    public String getCARNET() {
        return cARNET;
    }

    public void setCARNET(String cARNET) {
        this.cARNET = cARNET;
    }

    public String getCEDULA() {
        return cEDULA;
    }

    public void setCEDULA(String cEDULA) {
        this.cEDULA = cEDULA;
    }

    public String getDIRCLI() {
        return dIRCLI;
    }

    public void setDIRCLI(String dIRCLI) {
        this.dIRCLI = dIRCLI;
    }

    public String getDIS() {
        return dIS;
    }

    public void setDIS(String dIS) {
        this.dIS = dIS;
    }

    public String getUBICLI() {
        return uBICLI;
    }

    public void setUBICLI(String uBICLI) {
        this.uBICLI = uBICLI;
    }

    public String getUBIGEOCOMPLETO() {
        return uBIGEOCOMPLETO;
    }

    public void setUBIGEOCOMPLETO(String uBIGEOCOMPLETO) {
        this.uBIGEOCOMPLETO = uBIGEOCOMPLETO;
    }

    public String getTELE01() {
        return tELE01;
    }

    public void setTELE01(String tELE01) {
        this.tELE01 = tELE01;
    }

    public String getTELE02() {
        return tELE02;
    }

    public void setTELE02(String tELE02) {
        this.tELE02 = tELE02;
    }

    public String getCELULAR() {
        return cELULAR;
    }

    public void setCELULAR(String cELULAR) {
        this.cELULAR = cELULAR;
    }

    public String getEMAIL() {
        return eMAIL;
    }

    public void setEMAIL(String eMAIL) {
        this.eMAIL = eMAIL;
    }

    public String getCODVEN() {
        return cODVEN;
    }

    public void setCODVEN(String cODVEN) {
        this.cODVEN = cODVEN;
    }

    public String getFECUC() {
        return fECUC;
    }

    public void setFECUC(String fECUC) {
        this.fECUC = fECUC;
    }

    public String getFECUP() {
        return fECUP;
    }

    public void setFECUP(String fECUP) {
        this.fECUP = fECUP;
    }

    public String getCREDITO() {
        return cREDITO;
    }

    public void setCREDITO(String cREDITO) {
        this.cREDITO = cREDITO;
    }

    public String getOBS1() {
        return oBS1;
    }

    public void setOBS1(String oBS1) {
        this.oBS1 = oBS1;
    }

    public String getOBS2() {
        return oBS2;
    }

    public void setOBS2(String oBS2) {
        this.oBS2 = oBS2;
    }

    public Integer getCAUTION() {
        return cAUTION;
    }

    public void setCAUTION(Integer cAUTION) {
        this.cAUTION = cAUTION;
    }

    public String getCLASE() {
        return cLASE;
    }

    public void setCLASE(String cLASE) {
        this.cLASE = cLASE;
    }

    public String getRUCLE() {
        return rUCLE;
    }

    public void setRUCLE(String rUCLE) {
        this.rUCLE = rUCLE;
    }

    public Integer getACTIVO() {
        return aCTIVO;
    }

    public void setACTIVO(Integer aCTIVO) {
        this.aCTIVO = aCTIVO;
    }

    public String getZONA() {
        return zONA;
    }

    public void setZONA(String zONA) {
        this.zONA = zONA;
    }

    public String getZONAAUX() {
        return zONAAUX;
    }

    public void setZONAAUX(String zONAAUX) {
        this.zONAAUX = zONAAUX;
    }

    public String getSEMAFORO() {
        return sEMAFORO;
    }

    public void setSEMAFORO(String sEMAFORO) {
        this.sEMAFORO = sEMAFORO;
    }

    public String getFECINIVIG() {
        return fECINIVIG;
    }

    public void setFECINIVIG(String fECINIVIG) {
        this.fECINIVIG = fECINIVIG;
    }

    public String getFECFINVIG() {
        return fECFINVIG;
    }

    public void setFECFINVIG(String fECFINVIG) {
        this.fECFINVIG = fECFINVIG;
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

    public String getDniType() {
        return dniType;
    }

    public void setDniType(String dniType) {
        this.dniType = dniType;
    }

}