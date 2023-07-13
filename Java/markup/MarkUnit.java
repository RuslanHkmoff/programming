package markup;

import java.util.List;

public abstract class MarkUnit implements Markdown {
    protected List<Markdown> content;
    protected String openMd;
    protected String closeMd;
    protected String openTex;
    protected String closeTex;

    protected MarkUnit(List<Markdown> content, String openMd, String closeMd,  String openTex, String closeTex) {
        this.content = content;
        this.openMd = openMd;
        this.closeMd = closeMd;
        this.openTex = openTex;
        this.closeTex = closeTex;
    }

    public void toMarkdown(StringBuilder sb) {
        sb.append(openMd);
        for (Markdown mark : content) {
            mark.toMarkdown(sb);
        }
        sb.append(closeMd);
    }

    public void toTex(StringBuilder sb) {
        sb.append(openTex);
        for (Markdown mark : content) {
            mark.toTex(sb);
        }
        sb.append(closeTex);
    }
}

