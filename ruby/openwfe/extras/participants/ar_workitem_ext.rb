require 'openwfe/extras/participants/ar_participants'

module OpenWFE::Extras
  class ArWorkitem
    #TODO: give it a better name
    def self.convert_to_in_flow_work_item(ar_work_item_id)
      # history_log('proceeded', :fei => owi.fei)
      wi = OpenWFE::Extras::ArWorkitem.destroy(ar_work_item_id)
      wi.to_owfe_workitem
    end
  end
end