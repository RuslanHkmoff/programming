package markup;

import java.util.List;

public class Strikeout extends MarkUnit {
    public Strikeout(List<Markdown> content) {
        super(content, "~",  "~", "\\textst{", "}");
    }
}