require 'openwfe/extras/engine/db_persisted_engine'
require 'openwfe/extras/expool/db_history'
require 'openwfe/extras/expool/ar_expstorage'
require 'active_record_config'

module OpenWFE::Extras
  class ArDbPersistedEngine < DbPersistedEngine

    protected

    def build_expression_storage
      init_storage(ArExpressionStorage)
    end
  end
end

application_context = {}

application_context[:engine_name] = "ar_engine"
application_context[:definition_in_launchitem_allowed] = true
#jruby yaml has bug
application_context[:persist_as_yaml] = false

application_context[:work_directory] = java.lang.System.getProperty("engine_work_dir")
raise "Must specify system property 'engine_work_dir' for workflow engine!" unless application_context[:work_directory]

FileUtils.mkdir('logs') unless File.exist?('logs')
require 'logger'
application_context[:logger] = Logger.new('logs/ruote.log')
application_context[:logger].level = Logger::DEBUG

ActiveRecord::Base.logger = application_context[:logger]
establish_active_record_connection

engine = OpenWFE::Extras::ArDbPersistedEngine.new(application_context)

engine.init_service(:s_history, OpenWFE::Extras::QueuedDbHistory)
engine