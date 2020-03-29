package si.fri.mag.utils;

import si.fri.mag.DTO.responses.LinkMediaChunkDTO;
import si.fri.mag.converters.LinkMediaChunkConverter;
import si.fri.mag.entities.LinkMediaChunksEntity;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class ConverterUtil {

    @Inject
    private LinkMediaChunkConverter linkMediaChunkConverter;

    public List<LinkMediaChunkDTO> convertMediaLinkChunkMetadata(List<LinkMediaChunksEntity> linkMediaChunksEntities){
        List<LinkMediaChunkDTO> linkMediaChunkDTOS = new ArrayList<LinkMediaChunkDTO>();
        for(LinkMediaChunksEntity linkMediaChunk : linkMediaChunksEntities) {
            linkMediaChunkDTOS.add(linkMediaChunkConverter.toDTO(linkMediaChunk));
        }

        return linkMediaChunkDTOS;
    }

}
