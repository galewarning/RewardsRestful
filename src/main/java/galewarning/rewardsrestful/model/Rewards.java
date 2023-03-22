package galewarning.rewardsrestful.model;

import lombok.Data;

@Data
public class Rewards {
    private Long customerId;
    private Long firstMonthRewardPoints;
    private Long secondMonthRewardPoints;
    private Long thirdMonthRewardPoints;
    private Long totalRewardPoints;

    public Rewards() {
    }

    public Rewards(Long customerId, Long firstMonthRewardPoints, Long secondMonthRewardPoints, Long thirdMonthRewardPoints, Long totalRewardPoints) {
        this.customerId = customerId;
        this.firstMonthRewardPoints = firstMonthRewardPoints;
        this.secondMonthRewardPoints = secondMonthRewardPoints;
        this.thirdMonthRewardPoints = thirdMonthRewardPoints;
        this.totalRewardPoints = totalRewardPoints;
    }
}
