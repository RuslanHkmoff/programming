package markup;

import java.util.List;

public class Paragraph implements Markdown {
    private List<Markdown> content;

    public Paragraph(List<Markdown> content) {
        this.content = content;
    }

    public void toMarkdown(StringBuilder sb) {
        for (Markdown Markdown : content) {
            Markdown.toMarkdown(sb);
        }
    }
    public void toTex(StringBuilder sb) {
        for (Markdown Markdown : content) {
            Markdown.toTex(sb);
        }
    }
}