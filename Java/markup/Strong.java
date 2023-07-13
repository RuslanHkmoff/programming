package markup;

import java.util.List;

public class Strong extends MarkUnit {
    public Strong(List<Markdown> content) {
        super(content, "__", "__","\\textbf{", "}");
    }
}