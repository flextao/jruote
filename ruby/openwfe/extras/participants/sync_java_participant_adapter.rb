require 'openwfe/participants/participant'

module OpenWFE::Extras
  class SyncJavaParticipantAdapter
    include OpenWFE::LocalParticipant
    include com.flextao.jruote.Participant

    def initialize(participant)
      super()
      @participant = participant
    end

    def consume(workitem)
      @participant.consume(workitem)
      reply_to_engine workitem
    end

    def cancel(cancelitem)
      @participant.cancel(workitem)
    end

    def do_not_thread
      @participant.do_not_thread
    end
  end
end