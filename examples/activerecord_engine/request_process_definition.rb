
class RequestProcessDefinition < OpenWFE::ProcessDefinition
  sequence do
    set :f => "launcher", :value => "${launcher}"
    set :f => "entity_id", :value => "${entity_id}"
    set :f => "entity_type", :value => "${entity_type}"
    manager_of_launcher
    complete_entity
  end
end
