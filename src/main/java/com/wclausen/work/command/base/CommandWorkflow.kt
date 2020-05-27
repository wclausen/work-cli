package com.wclausen.work.command.base

import com.github.michaelbull.result.Result
import com.squareup.workflow.StatefulWorkflow
import com.squareup.workflow.Workflow

interface CommandWorkflow<in PropsT, OutputT : Result<*, *>> : Workflow<PropsT, OutputT, Command>

abstract class StatefulCommandWorkflow<in PropsT, StateT, OutputT : Result<*, *>> : CommandWorkflow<PropsT, OutputT>, StatefulWorkflow<PropsT, StateT, OutputT, Command>()

