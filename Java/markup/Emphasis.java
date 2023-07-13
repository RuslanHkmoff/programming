package markup;

import java.util.List;

public class Emphasis extends MarkUnit {
    public Emphasis(List<Markdown> content)
    {
        super(content, "*","*", "\\emph{", "}");
    }
}