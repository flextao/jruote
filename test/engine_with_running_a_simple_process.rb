require 'openwfe/engine'
require 'logger'

include_class "com.flextao.jruote.LoggerParticipant"

process_definition = OpenWFE.process_definition :name => 'test' do
  sequence do
    alice
    bob
  end
end

application_context = {}
application_context[:engine_name] = "simple engine"
application_context[:definition_in_launchitem_allowed] = true
application_context[:logger] = Logger.new STDOUT
application_context[:logger].level = Logger::ERROR
engine = OpenWFE::Engine.new(application_context)

engine.register_participant 'alice', OpenWFE::Extras::SyncJavaParticipantAdapter.new(LoggerParticipant.new('alice'))
engine.register_participant 'bob', OpenWFE::Extras::SyncJavaParticipantAdapter.new(LoggerParticipant.new('bob'))

fei = engine.launch(process_definition)

engine.wait_for(fei)
engine