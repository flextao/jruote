
require 'openwfe/workitem'


module OpenWFE
  class CancelItem < InFlowItem

    def initialize (workitem)

      super()
      @flow_expression_id = workitem.fei.dup
      @attributes = workitem.attributes.dup
    end
  end
end
