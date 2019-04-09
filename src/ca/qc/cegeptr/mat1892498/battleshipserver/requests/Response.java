package ca.qc.cegeptr.mat1892498.battleshipserver.requests;

public class Response {
    private String json;

    public Response(String json){
        this.setJson(json);
    }

    public void setJson(String json) {
        this.json = json;
    }

    public String getJson() {
        return json;
    }

    public String toString(){
        return json;
    }
}
