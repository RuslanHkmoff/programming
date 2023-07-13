package markup;

public class Text implements Markdown {
    private String text;

    public Text(String text) {
        this.text = text;
    }

    public void toMarkdown(StringBuilder sb) {
        sb.append(text);
    }

    public void toTex(StringBuilder sb) {
        sb.append(text);
    }
}