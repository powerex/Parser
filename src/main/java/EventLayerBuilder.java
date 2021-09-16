public class EventLayerBuilder {
    public EventLayerBuilder() {}
    private EventLayer eventLayer = new EventLayer();

    public EventLayerBuilder setStartTime(String startTime) {
        this.eventLayer.setStartTime(startTime);
        return this;
    }

    public EventLayerBuilder setEndTime(String endTime) {
        this.eventLayer.setEndTime(endTime);
        return this;
    }

    public EventLayerBuilder setDate(String date) {
        this.eventLayer.setDate(date);
        return this;
    }

    public EventLayerBuilder setGroup(String group) {
        this.eventLayer.setGroup(group);
        return this;
    }

    public EventLayerBuilder setSubject(String subject) {
        this.eventLayer.setSubject(subject);
        return this;
    }

    public EventLayerBuilder setClassroom(String classroom) {
        this.eventLayer.setClassroom(classroom);
        return this;
    }

    public EventLayerBuilder setLection(boolean lection) {
        this.eventLayer.setLection(lection);
        return this;
    }

    public EventLayer build() {
        return eventLayer;
    }
}
