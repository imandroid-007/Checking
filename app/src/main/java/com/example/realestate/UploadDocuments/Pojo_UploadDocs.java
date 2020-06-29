package com.example.realestate.UploadDocuments;

public class Pojo_UploadDocs {

    String edtxHintName;
    String noteMessage;
    String docImgTitleName;
    String localFilePath;
    boolean hasInputIdNum, isUploadedToServer;


    public Pojo_UploadDocs(String edtxHintName, String noteMessage, String docImgTitleName, String localFilePath, boolean hasInputIdNum, boolean isUploadedToServer) {
        this.edtxHintName = edtxHintName;
        this.noteMessage = noteMessage;
        this.docImgTitleName = docImgTitleName;
        this.localFilePath = localFilePath;
        this.hasInputIdNum = hasInputIdNum;
        this.isUploadedToServer = isUploadedToServer;
    }


    public String getEdtxHintName() {
        return edtxHintName;
    }

    public void setEdtxHintName(String edtxHintName) {
        this.edtxHintName = edtxHintName;
    }

    public String getNoteMessage() {
        return noteMessage;
    }

    public void setNoteMessage(String noteMessage) {
        this.noteMessage = noteMessage;
    }

    public String getDocImgTitleName() {
        return docImgTitleName;
    }

    public void setDocImgTitleName(String docImgTitleName) {
        this.docImgTitleName = docImgTitleName;
    }

    public String getLocalFilePath() {
        return localFilePath;
    }

    public void setLocalFilePath(String localFilePath) {
        this.localFilePath = localFilePath;
    }

    public boolean isHasInputIdNum() {
        return hasInputIdNum;
    }

    public void setHasInputIdNum(boolean hasInputIdNum) {
        this.hasInputIdNum = hasInputIdNum;
    }

    public boolean isUploadedToServer() {
        return isUploadedToServer;
    }

    public void setUploadedToServer(boolean uploadedToServer) {
        isUploadedToServer = uploadedToServer;
    }
}





























