package org.zerock.board.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.zerock.board.dto.ReplyDTO;
import org.zerock.board.entity.Board;
import org.zerock.board.entity.Reply;
import org.zerock.board.repository.ReplyRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReplyServiceImpl implements ReplyService{
    private final ReplyRepository replyRepo;

    @Override
    public Long register(ReplyDTO replyDTO){
        Reply reply = dtoToEntity(replyDTO);

        replyRepo.save(reply);

        return reply.getRno();
    }

    @Override
    public List<ReplyDTO> getList(Long bno) {
        List<Reply> result = replyRepo.getRepliesByBoardOrderByRno(
                Board.builder()
                        .bno(bno)
                        .build());

        return result.stream().map(reply -> entityToDTO(reply)).collect(Collectors.toList());
    }


    @Override
    public void modify(ReplyDTO replyDTO) {
        Reply reply = dtoToEntity(replyDTO);

        replyRepo.save(reply);
    }

    @Override
    public void remove(Long rno) {
        replyRepo.deleteById(rno);
    }
}
