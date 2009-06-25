
class SimplestProcessDefinition < OpenWFE::ProcessDefinition
  sequence do
    alice :activity => "step 1"
    bob
  end
end
