package library.model;

/**
 * ��� ����������� ������ ������.
 */
public class ContentType {

    private Long id;
    private String mime;
    /**
     * ���������� ����� ����� ������� ����.
     */
    private String ext;

    public ContentType() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getMime() { return mime; }
    public void setMime(String mime) { this.mime = mime; }

    public String getExt() { return ext; }
    public void setExt(String ext) { this.ext = ext; }
}