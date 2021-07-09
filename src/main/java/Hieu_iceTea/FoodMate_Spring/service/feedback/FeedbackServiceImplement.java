package Hieu_iceTea.FoodMate_Spring.service.feedback;


import Hieu_iceTea.FoodMate_Spring.model.Feedback;
import Hieu_iceTea.FoodMate_Spring.repository.FeedbackRepository;
import Hieu_iceTea.FoodMate_Spring.service.base.BaseServiceImplement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class FeedbackServiceImplement extends BaseServiceImplement<Feedback, Integer> implements FeedbackService {

    //region Initialization - Autowired
    @Autowired
    private FeedbackRepository feedbackRepository;

    public FeedbackServiceImplement(FeedbackRepository repository) {
        super(repository);
    }
    //endregion

}
