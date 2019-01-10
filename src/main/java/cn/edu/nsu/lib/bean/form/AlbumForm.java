package cn.edu.nsu.lib.bean.form;


import cn.edu.nsu.lib.annotions.Valid;

/**
 * @author 墨盒
 * @version 1.0
 * @Date 2017/8/21
 * @Time 14:47
 * @Descorption
 */
public class AlbumForm {
    @Valid(minLength = 1, maxLength = 30)
    private String name;
    @Valid(regex = "[012]", minLength = 1, maxLength = 1)
    private String authority;
    @Valid(maxLength = 200)
    private String content;
    @Valid(regex = "[012]", minLength = 1, maxLength = 1)
    private String type;
    @Valid(minLength = 1, maxLength = 20,  emptyAble = true)
    private String question;
    @Valid(minLength = 1, maxLength = 30,  emptyAble = true)
    private String answer;

    private long owner;
    private long id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getOwner() {
        return owner;
    }

    public void setOwner(long owner) {
        this.owner = owner;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
