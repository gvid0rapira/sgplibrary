package library.model;


public class DocContent {
	   
    private Long id;
    private DocVersion doc;
    private ContentType cntType;
    private String fileName;
    private byte[] content;
	
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public DocVersion getDoc() { return doc; }
    public void setDoc(DocVersion doc) { this.doc = doc; }

    public ContentType getCntType() { return cntType; }
    public void setCntType(ContentType type) { cntType = type; }

    public String getFileName(){ return fileName; }
    public void setFileName(String name) { fileName = name; }

    public byte[] getContent() { return content; }
    public void setContent(byte[] content) { this.content = content; }
	
}
