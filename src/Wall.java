import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

interface Block{
    String getColor();
    String getMaterial();
}
interface Structure{
    Optional<Block> findBlockByColor(String color);
    List<Block> findBlocksByMaterial(String material);
    int count();
}
interface CompositeBlock extends Block{
    List<Block> getBlocks();
}

public class Wall implements Structure {

    private List<Block> blocks;

    @Override
    public Optional<Block> findBlockByColor(String color) {
        return blockStream()
                .filter(block -> block.getColor().equals(color))
                .findFirst();
    }

    @Override
    public List<Block> findBlocksByMaterial(String material) {
        return blockStream()
                .filter(block -> block.getMaterial().equals(material))
                .toList();
    }

    @Override
    public int count() {
        return (int) blockStream()
                .count();
    }

    public Stream<Block> blockStream(){
        return blocks.stream()
                .flatMap(block -> block instanceof CompositeBlock ?
                        ((CompositeBlock) block).getBlocks().stream() : Stream.of(block));

    }
}
